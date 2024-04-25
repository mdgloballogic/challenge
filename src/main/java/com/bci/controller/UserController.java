package com.bci.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bci.dto.UserDTO;
import com.bci.security.AuthorizationJWT;
import com.bci.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	private UserService userService;
	private AuthorizationJWT authorizationJWT;
	
	public UserController(UserService userService, AuthorizationJWT authorizationJWT) {
		this.userService = userService;
		this.authorizationJWT = authorizationJWT;
	}
	
    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> signUpUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.signup(userDTO));
    }

}
