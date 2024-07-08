package ru.tn.nnjack.GismeteoApiService.service;


import org.springframework.stereotype.Service;
import ru.tn.nnjack.GismeteoApiService.client.GismeteoClient;
import ru.tn.nnjack.GismeteoApiService.dto.CurrentWeatherDTO;
import ru.tn.nnjack.GismeteoApiService.dto.WeatherDTO;
import ru.tn.nnjack.GismeteoApiService.dto.WeatherForecastDTO;
import ru.tn.nnjack.GismeteoApiService.models.WeatherModel;
import ru.tn.nnjack.GismeteoApiService.models.utils.mapper.WeatherMapper;

import java.util.List;

@Service
public class WeatherService {

    private final GismeteoClient gismeteoClient;
    private final WeatherMapper weatherMapper;

    public WeatherService(GismeteoClient gismeteoClient, WeatherMapper weatherMapper) {
        this.gismeteoClient = gismeteoClient;
        this.weatherMapper = weatherMapper;
    }

    public WeatherModel getCurrentWeather(int id, String lang) {
        CurrentWeatherDTO currentWeatherDTO =  gismeteoClient.getCurrentWeather(id, lang);
        WeatherDTO weatherDTO = currentWeatherDTO.getResponse();
        WeatherModel model = weatherMapper.weatherDtoToModel(weatherDTO);
        model.setPrecipitationType(currentWeatherDTO.getResponse().getPrecipitation().getType(),lang);
        model.setWindDirection(currentWeatherDTO.getResponse().getWind().getDirection().getScale_8(),lang);
        return model;
    }

    public List<WeatherModel> getForecastWeather(int id, String lang, int days){
        WeatherForecastDTO weatherForecastDTO = gismeteoClient.getForecastWeather(id,days, lang);
        List<WeatherDTO> weatherDTOList = weatherForecastDTO.getResponse();
        List<WeatherModel> weatherModels = weatherMapper.weatherDtoListToModelList(weatherDTOList);
        for (int i = 0; i < weatherModels.size(); i++) {
            WeatherModel model = weatherModels.get(i);
            WeatherDTO dto = weatherDTOList.get(i);

            model.setPrecipitationType(dto.getPrecipitation().getType(), lang);
            model.setWindDirection(dto.getWind().getDirection().getScale_8(), lang);
        }
        return weatherModels;
    }

}
