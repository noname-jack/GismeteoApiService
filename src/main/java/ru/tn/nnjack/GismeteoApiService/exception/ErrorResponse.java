package ru.tn.nnjack.GismeteoApiService.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorResponse {
    private Date timestamp;
    private String message;
    private int statusCode;

    public ErrorResponse(Date timestamp, String message, int statusCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.statusCode = statusCode;
    }

}
