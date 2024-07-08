package ru.tn.nnjack.GismeteoApiService.models;

import lombok.Getter;
import lombok.Setter;
import ru.tn.nnjack.GismeteoApiService.models.utils.PrecipitationType;
import ru.tn.nnjack.GismeteoApiService.models.utils.WindDirection;

@Getter
@Setter
public class WeatherModel {
    private long dateUnix;
    private double temperatureAirC;
    private String icon;
    private String descriptionFull;
    private String precipitationType;
    private double precipitationAmount;
    private String windDirection;
    private int windSpeedMS;
    private int cityCode;
    public void setPrecipitationType(int precipitationType, String langCode) {
        String precipitation;
        if ("RU".equalsIgnoreCase(langCode)) {
            PrecipitationType type = PrecipitationType.getByCode(precipitationType);
            precipitation = type.getDescriptionRU();
        } else {
            PrecipitationType type = PrecipitationType.getByCode(precipitationType);
            precipitation = type.getDescriptionEN();
        }
        this.precipitationType = precipitation;
    }

    public void setWindDirection(int windDirection, String langCode) {
        String wind;
        if ("RU".equalsIgnoreCase(langCode)) {
            WindDirection type = WindDirection.getByCode(windDirection);
            wind = type.getDescriptionRU();
        } else {
            WindDirection type = WindDirection.getByCode(windDirection);
            wind = type.getDescriptionEN();
        }
        this.windDirection = wind;
    }
}


