package ru.tn.nnjack.GismeteoApiService.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.tn.nnjack.GismeteoApiService.client.GismeteoClient;
import ru.tn.nnjack.GismeteoApiService.dto.CurrentWeatherDTO;
import ru.tn.nnjack.GismeteoApiService.dto.WeatherDTO;
import ru.tn.nnjack.GismeteoApiService.dto.WeatherForecastDTO;

import ru.tn.nnjack.GismeteoApiService.models.WeatherModel;
import ru.tn.nnjack.GismeteoApiService.models.utils.mapper.WeatherMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class WeatherServiceTest {

    @Mock
    private GismeteoClient gismeteoClient;

    @InjectMocks
    private WeatherService weatherService;

    @Autowired
    private WeatherMapper weatherMapper;

    private CurrentWeatherDTO currentWeatherDTO;
    private WeatherForecastDTO weatherForecastDTO;

    @BeforeEach
    void setUp() {
        weatherService = new WeatherService(gismeteoClient, weatherMapper);
        currentWeatherDTO = createCurrentWeatherDTO();
        weatherForecastDTO = createWeatherForecastDTO();
    }



    @Test
    void getCurrentWeather_ShouldReturnWeatherModel() {
        when(gismeteoClient.getCurrentWeather(anyInt(), anyString())).thenReturn(currentWeatherDTO);

        WeatherModel result = weatherService.getCurrentWeather(1, "en");

        assertEquals(20.0, result.getTemperatureAirC());
        assertEquals("d", result.getIcon());
        assertEquals("Clear sky", result.getDescriptionFull());

        verify(gismeteoClient).getCurrentWeather(anyInt(), anyString());
    }

    @Test
    void getForecastWeather_ShouldReturnWeatherModelList() {
        when(gismeteoClient.getForecastWeather(anyInt(), anyInt(), anyString())).thenReturn(weatherForecastDTO);

        List<WeatherModel> result = weatherService.getForecastWeather(1, "en", 5);

        assertEquals(1, result.size());
        WeatherModel model = result.getFirst();
        assertEquals(20.0, model.getTemperatureAirC());
        assertEquals("d", model.getIcon());
        assertEquals("Clear sky", model.getDescriptionFull());

        verify(gismeteoClient).getForecastWeather(anyInt(), anyInt(), anyString());
    }


    private CurrentWeatherDTO createCurrentWeatherDTO() {
        WeatherDTO weatherDTO = new WeatherDTO();
        WeatherDTO.TemperatureInfo.Temperature temperature = new WeatherDTO.TemperatureInfo.Temperature();
        temperature.setC(20.0);
        WeatherDTO.TemperatureInfo tempInfo = new WeatherDTO.TemperatureInfo();
        tempInfo.setAir(temperature);
        weatherDTO.setTemperature(tempInfo);

        weatherDTO.setIcon("d");

        WeatherDTO.DescriptionInfo descriptionInfo = new WeatherDTO.DescriptionInfo();
        descriptionInfo.setFull("Clear sky");
        weatherDTO.setDescription(descriptionInfo);

        WeatherDTO.PrecipitationInfo precipitationInfo = new WeatherDTO.PrecipitationInfo();
        precipitationInfo.setType(0);
        weatherDTO.setPrecipitation(precipitationInfo);

        WeatherDTO.WindInfo windInfo = new WeatherDTO.WindInfo();
        WeatherDTO.WindInfo.Direction direction = new WeatherDTO.WindInfo.Direction();
        direction.setScale_8(3);
        windInfo.setDirection(direction);
        weatherDTO.setWind(windInfo);

        CurrentWeatherDTO currentWeatherDTO = new CurrentWeatherDTO();
        currentWeatherDTO.setResponse(weatherDTO);

        return currentWeatherDTO;
    }

    private WeatherForecastDTO createWeatherForecastDTO() {
        WeatherDTO weatherDTO = new WeatherDTO();
        WeatherDTO.TemperatureInfo.Temperature temperature = new WeatherDTO.TemperatureInfo.Temperature();
        temperature.setC(20.0);
        WeatherDTO.TemperatureInfo tempInfo = new WeatherDTO.TemperatureInfo();
        tempInfo.setAir(temperature);
        weatherDTO.setTemperature(tempInfo);

        weatherDTO.setIcon("d");

        WeatherDTO.DescriptionInfo descriptionInfo = new WeatherDTO.DescriptionInfo();
        descriptionInfo.setFull("Clear sky");
        weatherDTO.setDescription(descriptionInfo);

        WeatherDTO.PrecipitationInfo precipitationInfo = new WeatherDTO.PrecipitationInfo();
        precipitationInfo.setType(0);
        weatherDTO.setPrecipitation(precipitationInfo);

        WeatherDTO.WindInfo windInfo = new WeatherDTO.WindInfo();
        WeatherDTO.WindInfo.Direction direction = new WeatherDTO.WindInfo.Direction();
        direction.setScale_8(3);
        windInfo.setDirection(direction);
        weatherDTO.setWind(windInfo);

        WeatherForecastDTO weatherForecastDTO = new WeatherForecastDTO();
        weatherForecastDTO.setResponse(List.of(weatherDTO));

        return weatherForecastDTO;
    }
}

