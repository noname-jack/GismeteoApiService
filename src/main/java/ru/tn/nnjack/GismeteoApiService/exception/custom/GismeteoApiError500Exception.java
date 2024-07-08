package ru.tn.nnjack.GismeteoApiService.exception.custom;

public class GismeteoApiError500Exception extends GismeteoApiBaseErrorException {
    public GismeteoApiError500Exception(int status, String message) {
        super(status, message);
    }
}
