package com.bci;

import com.bci.dto.SignUpRequestDTO;
import com.bci.dto.UserResponseDTO;
import com.bci.model.Phone;
import com.bci.model.User;
import com.bci.repository.UserRepository;
import com.bci.service.JwtService;
import com.bci.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestApp {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createNewUser() {

        SignUpRequestDTO request = new SignUpRequestDTO();
        request.setName("Nombre Apellido");
        request.setEmail("nombre@dominio.ar");
        request.setPassword("a2asfGfdfdf4");
        List<Phone> phones = new ArrayList<>(Collections.singletonList(new Phone(12345678, 11, "00")));
        request.setPhones(phones);

        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(jwtService.generateToken(any(User.class))).thenReturn("generatedToken");

        UserResponseDTO response = userService.createUser(request);

        assertNotNull(response);
        assertEquals("generatedToken", response.getToken());
        assertTrue(response.isActive());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void createExistingUser() {
        SignUpRequestDTO request = new SignUpRequestDTO();
        request.setEmail("nombre@dominio.ar");

        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(new User()));

        try {
            userService.createUser(request);
            fail("Se esperaba una excepción");
        } catch (RuntimeException e) {
            assertEquals("El usuario ya existe", e.getMessage());
        }
    }

    @Test
    public void userLoginValidToken() {

        String token = "validToken";
        String email = "nombre@dominio.ar";
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(email);
        user.setToken("generatedToken");
        user.setActive(true);

        when(jwtService.extractEmail(token)).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserResponseDTO response = userService.login(token);

        assertNotNull(response);
        assertEquals(user.getId(), response.getId());
        assertEquals(user.getToken(), response.getToken());
        assertEquals(user.isActive(), response.isActive());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void userLoginInvalidToken() {

        String token = "invalidToken";

        when(jwtService.extractEmail(token)).thenReturn("nombre@dominio.ar");
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());

        try {
            userService.login(token);
            fail("Se esperaba una excepción");
        } catch (RuntimeException e) {
            assertEquals("Usuario no encontrado", e.getMessage());
        }
    }
}