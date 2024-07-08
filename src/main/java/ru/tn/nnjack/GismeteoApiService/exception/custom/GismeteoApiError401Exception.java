package ru.tn.nnjack.GismeteoApiService.exception.custom;


public class GismeteoApiError401Exception extends GismeteoApiBaseErrorException {
    public GismeteoApiError401Exception(int status, String message) {
        super(status, message);
    }
}
