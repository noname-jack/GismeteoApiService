package ru.tn.nnjack.GismeteoApiService.exception.custom;

public class GismeteoApiError404Exception extends GismeteoApiBaseErrorException {
    public GismeteoApiError404Exception(int status, String message) {
        super(status, message);
    }
}
