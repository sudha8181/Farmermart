package com.tek.FarmerMart.UserMgmt.exception.vendor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class VendorServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(VendorNotFoundException.class)
    public ResponseEntity<Object> handleUserAndPasswordNotValidException(VendorNotFoundException exception) {
        return buildResponse(HttpStatus.NOT_FOUND, exception.getMsg());
    }

    @ExceptionHandler(PhoneNumberException.class)
    public ResponseEntity<Object> handlePhoneNumberException(PhoneNumberException exception) {
        return buildResponse(HttpStatus.BAD_REQUEST, exception.getMsg());
    }


    @ExceptionHandler(AadhaarNumberException.class)
    public ResponseEntity<Object> handleAadhaarException(AadhaarNumberException exception) {
        return buildResponse(HttpStatus.BAD_REQUEST, exception.getMsg());
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

