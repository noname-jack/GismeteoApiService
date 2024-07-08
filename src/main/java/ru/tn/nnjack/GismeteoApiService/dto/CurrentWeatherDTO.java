package ru.tn.nnjack.GismeteoApiService.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CurrentWeatherDTO {

    private Meta meta;
    private WeatherDTO response;
    @Getter
    @Setter
    public static class Meta {
        private String message;
        private String code;
    }
}
