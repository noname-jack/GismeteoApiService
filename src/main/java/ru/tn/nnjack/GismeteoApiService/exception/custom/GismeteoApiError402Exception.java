package ru.tn.nnjack.GismeteoApiService.exception.custom;

public class GismeteoApiError402Exception extends GismeteoApiBaseErrorException {
    public GismeteoApiError402Exception(int status, String message) {
        super(status, message);
    }
}
