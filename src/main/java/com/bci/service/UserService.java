package com.bci.service;

import com.bci.dto.SignUpRequestDTO;
import com.bci.dto.UserResponseDTO;
import com.bci.model.Phone;
import com.bci.model.User;
import com.bci.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(SignUpRequestDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhones(request.getPhones().stream()
                .map(phoneRequest -> new Phone(phoneRequest.getNumber(), phoneRequest.getCityCode(), phoneRequest.getCountryCode()))
                .collect(Collectors.toList()));
        user.setCreated(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(jwtService.generateToken(user));
        user.setActive(true);

        userRepository.save(user);

        return UserResponseDTO.fromUserForSignUp(user);
    }

    public UserResponseDTO login(String token) {
        String email = jwtService.extractEmail(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setLastLogin(LocalDateTime.now());
        user.setToken(jwtService.generateToken(user));
        userRepository.save(user);

        return UserResponseDTO.fromUserForLogin(user);
    }
}
