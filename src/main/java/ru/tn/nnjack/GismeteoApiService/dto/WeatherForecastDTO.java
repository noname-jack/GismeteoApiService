package ru.tn.nnjack.GismeteoApiService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherForecastDTO {

    private Meta meta;
    private List<WeatherDTO> response;
    @Getter
    @Setter
    public static class Meta {
        private String message;
        private String code;
    }
}
