package com.tek.FarmerMart.UserMgmt.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserServiceExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserAndPasswordNotValidException(UserNotFoundException exception) {
        return buildResponse(HttpStatus.BAD_REQUEST, exception.getMsg());
    }

    @ExceptionHandler(PasswordMisMatchException.class)
    public ResponseEntity<Object> handlerPassword(PasswordMisMatchException ex){
        return buildResponse(HttpStatus.BAD_REQUEST,ex.getMsg());
    }




    private ResponseEntity<Object> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, status);
    }

}
