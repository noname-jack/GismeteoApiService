package ru.tn.nnjack.GismeteoApiService.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import ru.tn.nnjack.GismeteoApiService.models.utils.deserializer.ResponseDataDeserializer;

import java.util.List;

@Getter
@Setter
public class CitiesDto {
    private Meta meta;
    private ResponseData response;
    @Getter
    @Setter
    public static class Meta {
        private String message;
        private String code;
    }
    @Getter
    @Setter
    @JsonDeserialize(using = ResponseDataDeserializer.class)
    public static class ResponseData {
        private List<City> items;
        private int total;
    }
    @Getter
    @Setter
    public static class City {
        private int id;
        private String name;
        private String kind;
        private Country country;
        private District district;
        private SubDistrict subDistrict;
    }
    @Getter
    @Setter
    public static class SubDistrict {
        private String name;
    }
    @Getter
    @Setter
    public static class District {
        private String name;
    }
    @Getter
    @Setter
    public static class Country {
        private String code;
    }
}
