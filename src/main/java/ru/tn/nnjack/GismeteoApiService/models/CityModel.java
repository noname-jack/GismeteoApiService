package ru.tn.nnjack.GismeteoApiService.models;

import lombok.Getter;
import lombok.Setter;
import ru.tn.nnjack.GismeteoApiService.models.utils.GeographicalObjectType;

@Getter
@Setter
public class CityModel {
    private int id;
    private String name;
    private String kind;
    private String country;
    private String district;
    private String subDistrict;

    public void setKind(String kind, String langCode){
        String kindGeographicalObject;
        GeographicalObjectType type = GeographicalObjectType.getDescriptionByCode(kind,langCode);
        if ("RU".equalsIgnoreCase(langCode)) {
            kindGeographicalObject = type.getDescriptionRu();
        } else {
            kindGeographicalObject = type.getDescriptionEn();
        }
        this.kind = kindGeographicalObject;
    }
}
