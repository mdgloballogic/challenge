package com.bci.security;

import java.time.LocalDate;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.*;

import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class RestAdvicer {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseHandler> passwordNullException(){
    	ResponseHandler responseHandler = ResponseHandler.builder()
                .timeStamp(LocalDate.now())
                .code(HttpStatus.BAD_REQUEST.toString())
                .detailMessage("La contraseña no puede estar vacía.")
                .build();

        return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseHandler> emailNullException(){
    	ResponseHandler responseHandler = ResponseHandler.builder()
                 .timeStamp(LocalDate.now())
                 .code(HttpStatus.BAD_REQUEST.toString())
                 .detailMessage("Email no puede estar vacío.")
                 .build();

         return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ResponseHandler> tokenException(SignatureException signatureException){
    	ResponseHandler responseHandler = ResponseHandler.builder()
                .timeStamp(LocalDate.now())
                .code(HttpStatus.UNAUTHORIZED.toString())
                .detailMessage(signatureException.getMessage())
                .build();

        return new ResponseEntity<>(responseHandler, HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ResponseHandler> repositoryException(GenericException genericException){
    	ResponseHandler responseHandler = ResponseHandler.builder()
                 .timeStamp(LocalDate.now())
                 .code(genericException.getCode())
                 .detailMessage(genericException.getMessage())
                 .build();

         return new ResponseEntity<>(responseHandler, genericException.getHttpStatus());
    }
}
