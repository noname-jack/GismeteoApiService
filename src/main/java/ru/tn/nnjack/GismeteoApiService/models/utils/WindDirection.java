package ru.tn.nnjack.GismeteoApiService.models.utils;

import lombok.Getter;

@Getter
public enum WindDirection {
    CALM(0, "Calm", "Штиль"),
    NORTH(1, "North", "Северный"),
    NORTHEAST(2, "Northeast", "Северо-восточный"),
    EAST(3, "East", "Восточный"),
    SOUTHEAST(4, "Southeast", "Юго-восточный"),
    SOUTH(5, "South", "Южный"),
    SOUTHWEST(6, "Southwest", "Юго-западный"),
    WEST(7, "West", "Западный"),
    NORTHWEST(8, "Northwest", "Северо-западный");

    private final int code;
    private final String descriptionEN;
    private final String descriptionRU;

    WindDirection(int code, String descriptionEN, String descriptionRU) {
        this.code = code;
        this.descriptionEN = descriptionEN;
        this.descriptionRU = descriptionRU;
    }


    public static WindDirection getByCode(int code) {
        for (WindDirection direction : values()) {
            if (direction.code == code) {
                return direction;
            }
        }
        return WindDirection.CALM;
    }
}
