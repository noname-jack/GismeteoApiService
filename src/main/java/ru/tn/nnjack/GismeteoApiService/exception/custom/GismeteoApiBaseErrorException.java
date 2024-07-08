package ru.tn.nnjack.GismeteoApiService.exception.custom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GismeteoApiBaseErrorException extends RuntimeException {
    private int status;

    public GismeteoApiBaseErrorException(int status, String message) {
        super(message);
        this.status = status;
    }
}
