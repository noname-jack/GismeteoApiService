package ru.tn.nnjack.GismeteoApiService.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import ru.tn.nnjack.GismeteoApiService.exception.custom.*;

import java.io.IOException;
import java.io.InputStream;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 400 -> {
                try(InputStream responseBodyStream = response.body().asInputStream()) {
                    ObjectMapper mapper = new ObjectMapper();
                    return mapper.readValue(responseBodyStream, GismeteoApiError400Exception.class);
                } catch (IOException e) {
                    return new RuntimeException("Failed to process error response for 400 status");
                }
            }
            case 401 ->
            {
                return new GismeteoApiError401Exception(401, "It is impossible to access the Gismeteo Api due to an invalid token");
            }
            case 402 -> {
                return new GismeteoApiError402Exception(402, "Free requests to the Gismeteo server have ended");
            }
            case 403 -> {
                return new GismeteoApiError403Exception(403, "Access restricted");
            }
            case 404 ->
            {
                return new GismeteoApiError404Exception(404,"Incorrect address entered, or such page does not exist");
            }
            case 500 -> {
                return new GismeteoApiError500Exception(500, "Error on the Gismeteo server side");
            }
            case 504 -> {
                return new GismeteoApiError504Exception(504, "The waiting time from Gismeteo has been exceeded");
            }
            default -> {
                return defaultErrorDecoder.decode(methodKey, response);
            }
        }
    }
}
