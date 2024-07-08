package ru.tn.nnjack.GismeteoApiService.exception.custom;

public class GismeteoApiError504Exception extends GismeteoApiBaseErrorException {
    public GismeteoApiError504Exception(int status, String message) {
        super(status, message);
    }
}
