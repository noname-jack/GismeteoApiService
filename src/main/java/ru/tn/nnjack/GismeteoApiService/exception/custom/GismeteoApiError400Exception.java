package ru.tn.nnjack.GismeteoApiService.exception.custom;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GismeteoApiError400Exception extends RuntimeException {
    private Meta meta;
    private Response weatherResponse;
    @Setter
    @Getter
    public static class Meta {
        private String message;
        private int code;
    }
    @Setter
    @Getter
    public static  class Response{

    }
}
