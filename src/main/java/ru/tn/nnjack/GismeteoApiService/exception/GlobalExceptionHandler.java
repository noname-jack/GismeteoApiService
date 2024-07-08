package ru.tn.nnjack.GismeteoApiService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tn.nnjack.GismeteoApiService.exception.custom.*;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GismeteoApiError400Exception.class)
    public ResponseEntity<ErrorResponse> handleGismeteoApiError404Exception(GismeteoApiError400Exception e) {
        ErrorResponse exceptionDetails = new ErrorResponse(new Date(), e.getMeta().getMessage(), e.getMeta().getCode());
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(GismeteoApiError401Exception.class)
    public ResponseEntity<ErrorResponse> handleGismeteoApiError401Exception(GismeteoApiError401Exception e){
        return new ResponseEntity<>(new ErrorResponse(new Date(), e.getMessage(), e.getStatus()), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(GismeteoApiError402Exception.class)
    public ResponseEntity<ErrorResponse> handleGismeteoApiError401Exception(GismeteoApiError402Exception e){
        return new ResponseEntity<>(new ErrorResponse(new Date(), e.getMessage(), e.getStatus()), HttpStatus.PAYMENT_REQUIRED);
    }
    @ExceptionHandler(GismeteoApiError403Exception.class)
    public ResponseEntity<ErrorResponse> handleGismeteoApiError401Exception(GismeteoApiError403Exception e){
        return new ResponseEntity<>(new ErrorResponse(new Date(), e.getMessage(), e.getStatus()), HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(GismeteoApiError404Exception.class)
    public ResponseEntity<ErrorResponse> handleGismeteoApiError401Exception(GismeteoApiError404Exception e){
        return new ResponseEntity<>(new ErrorResponse(new Date(), e.getMessage(), e.getStatus()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(GismeteoApiError500Exception.class)
    public ResponseEntity<ErrorResponse> handleGismeteoApiError401Exception(GismeteoApiError500Exception e){
        return new ResponseEntity<>(new ErrorResponse(new Date(), e.getMessage(), e.getStatus()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(GismeteoApiError504Exception.class)
    public ResponseEntity<ErrorResponse> handleGismeteoApiError401Exception(GismeteoApiError504Exception e){
        return new ResponseEntity<>(new ErrorResponse(new Date(), e.getMessage(), e.getStatus()), HttpStatus.GATEWAY_TIMEOUT);
    }

}
