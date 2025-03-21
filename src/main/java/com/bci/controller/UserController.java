package com.bci.controller;

import com.bci.dto.SignUpRequestDTO;
import com.bci.dto.UserResponseDTO;
import com.bci.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Api(tags = "User API")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registrar un nuevo usuario", notes = "Crea un nuevo usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario registrado exitosamente"),
            @ApiResponse(code = 400, message = "Solicitud inválida"),
            @ApiResponse(code = 409, message = "El usuario ya existe")
    })
    public ResponseEntity<UserResponseDTO> signUp(@RequestBody @Valid SignUpRequestDTO request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @ApiOperation(value = "Iniciar sesión", notes = "Inicia sesión con un token JWT válido")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Inicio de sesión exitoso"),
            @ApiResponse(code = 401, message = "Token inválido o expirado")
    })
    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> login(@RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        return ResponseEntity.ok(userService.login(jwtToken));
    }
}