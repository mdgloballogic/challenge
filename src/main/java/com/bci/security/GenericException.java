package com.bci.security;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String code;
    private final String message;
    private final HttpStatus httpStatus;
    
    public GenericException(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
