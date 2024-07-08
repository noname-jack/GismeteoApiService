package ru.tn.nnjack.GismeteoApiService.models.utils;

import lombok.Getter;

@Getter
public enum PrecipitationType {
    NO_PRECIPITATION(0, "No precipitation", "Нет осадков"),
    RAIN(1, "Rain", "Дождь"),
    SNOW(2, "Snow", "Снег"),
    MIXED_PRECIPITATION(3, "Mixed precipitation", "Смешанные осадки");
    private final int code;
    private final String descriptionEN;
    private final String descriptionRU;

    PrecipitationType(int code, String descriptionEN, String descriptionRU) {
        this.code = code;
        this.descriptionEN = descriptionEN;
        this.descriptionRU = descriptionRU;
    }


    public static PrecipitationType getByCode(int code) {
        for (PrecipitationType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return PrecipitationType.NO_PRECIPITATION;
    }
}
