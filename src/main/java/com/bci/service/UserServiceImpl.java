package com.bci.service;

import java.time.LocalDate;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bci.dto.UserDTO;
import com.bci.entity.User;
import com.bci.repository.UserRepository;
import com.bci.security.AuthorizationJWT;
import com.bci.security.GenericException;
import com.bci.security.RegexFilter;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
    private RegexFilter regexFilter;
    private PasswordEncoder passwordEncoder;
    private AuthorizationJWT authorizationToken;
    private ModelMapper mapper;

    public UserServiceImpl (UserRepository userRepository,
                            RegexFilter regexFilter,
                            AuthorizationJWT authorizationToken,
                            PasswordEncoder passwordEncoder,
                            ModelMapper mapper){
        this.userRepository = userRepository;
        this.regexFilter = regexFilter;
        this.authorizationToken = authorizationToken;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

	@Override
	public UserDTO signup(UserDTO userDto) {
		// TODO Auto-generated method stub
		
        Optional<User> newUser = userRepository.findByEmail(userDto.getEmail());

        if(newUser.isPresent()){
            throw new GenericException(
                    HttpStatus.CONFLICT.toString(),
                    "El correo ya está registrado.",
                    HttpStatus.CONFLICT
            );
        }
        
        if(!regexFilter.regexEmail(userDto.getEmail())){
            throw new GenericException(
                    HttpStatus.BAD_REQUEST.toString(),
                    "Formato de correo inválido.",
                    HttpStatus.CONFLICT
            );
        }

        if(!regexFilter.regexPassword(userDto.getPassword())){
            throw new GenericException(
                    HttpStatus.BAD_REQUEST.toString(),
                    "Formato de contraseña inválido.",
                    HttpStatus.CONFLICT
            );
        }

        userDto.setActive(true);
        userDto.setCreated(LocalDate.now());
        userDto.setModified(LocalDate.now());
        userDto.setLastLogin(LocalDate.now());

        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);

        String token = authorizationToken.authorize(userDto.getEmail());
        userDto.setToken(token);
        userDto.setId(user.getId());
		
		return null;
	}

}
