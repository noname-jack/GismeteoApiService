package ru.tn.nnjack.GismeteoApiService.models.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.tn.nnjack.GismeteoApiService.dto.CitiesDto;
import ru.tn.nnjack.GismeteoApiService.models.CityModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "kind", target = "kind"),
            @Mapping(source = "country.code", target = "country"),
            @Mapping(source = "district.name", target = "district"),
            @Mapping(source = "subDistrict.name", target = "subDistrict")
    })
    CityModel cityDtoToCityModel(CitiesDto.City city);
    List<CityModel> cityListToCityModelList(List<CitiesDto.City> cityList);
}
