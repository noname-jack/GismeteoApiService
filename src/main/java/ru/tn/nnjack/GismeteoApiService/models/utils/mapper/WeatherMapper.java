package ru.tn.nnjack.GismeteoApiService.models.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.tn.nnjack.GismeteoApiService.dto.WeatherDTO;
import ru.tn.nnjack.GismeteoApiService.models.WeatherModel;

import java.util.List;


@Mapper(componentModel = "spring")
public interface WeatherMapper {

    @Mapping(source = "date.unix", target = "dateUnix")
    @Mapping(source = "temperature.air.c", target = "temperatureAirC")
    @Mapping(source = "icon", target = "icon")
    @Mapping(source = "description.full", target = "descriptionFull")
    @Mapping(source = "precipitation.type", target = "precipitationType")
    @Mapping(source = "precipitation.amount", target = "precipitationAmount")
    @Mapping(source = "wind.direction.scale_8", target = "windDirection")
    @Mapping(source = "wind.speed.m_s", target = "windSpeedMS")
    @Mapping(source = "city", target = "cityCode")
    WeatherModel weatherDtoToModel(WeatherDTO weatherDTO);


    List<WeatherModel> weatherDtoListToModelList(List<WeatherDTO> weatherDTOList);
}
