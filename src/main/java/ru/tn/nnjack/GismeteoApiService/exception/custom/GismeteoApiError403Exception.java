package ru.tn.nnjack.GismeteoApiService.exception.custom;

public class GismeteoApiError403Exception extends GismeteoApiBaseErrorException {
    public GismeteoApiError403Exception(int status, String message) {
        super(status, message);
    }
}
