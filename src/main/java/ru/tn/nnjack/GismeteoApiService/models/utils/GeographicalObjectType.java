package ru.tn.nnjack.GismeteoApiService.models.utils;

import lombok.Getter;

@Getter
public enum GeographicalObjectType {
    T("Город", "City"),
    C("Мегаполис", "Metropolis"),
    A("Аэропорт", "Airport"),
    M("Метеостанция", "Weather Station");

    private final String descriptionRu;
    private final String descriptionEn;

    GeographicalObjectType(String descriptionRu, String descriptionEn) {
        this.descriptionRu = descriptionRu;
        this.descriptionEn = descriptionEn;
    }
    public static GeographicalObjectType getDescriptionByCode(String code, String langCode) {
        for (GeographicalObjectType geographicalObjectType : values()) {
            if (geographicalObjectType.name().equals(code)) {
                return geographicalObjectType;
            }
        }
        return GeographicalObjectType.T;
    }
}
