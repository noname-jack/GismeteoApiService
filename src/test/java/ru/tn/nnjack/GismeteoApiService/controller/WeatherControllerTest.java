package ru.tn.nnjack.GismeteoApiService.controller;


import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.tn.nnjack.GismeteoApiService.models.CityModel;
import ru.tn.nnjack.GismeteoApiService.models.WeatherModel;
import ru.tn.nnjack.GismeteoApiService.service.CitySearchService;
import ru.tn.nnjack.GismeteoApiService.service.WeatherService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @MockBean
    private CitySearchService citySearchService;


    private WeatherModel mockWeather;

    private CityModel mockCity;

    private List<CityModel> mockCityList;
    private List<WeatherModel> mockWeatherList;


    @SneakyThrows
    @BeforeEach
    void SetUp(){
        mockWeather = new WeatherModel();
        mockWeather.setCityCode(12345);
        mockWeather.setTemperatureAirC(25.0);
        mockWeather.setDescriptionFull("Clear sky");
        mockWeatherList = List.of(mockWeather);


        mockCity = new CityModel();
        mockCity.setId(1);
        mockCity.setName("Belgorod");
        mockCityList = List.of(mockCity);

    }

    @Test
    void testGetCurrentWeather_Success() throws Exception {
        int cityId = 12345;
        String lang = "en";

        when(weatherService.getCurrentWeather(cityId, lang)).thenReturn(mockWeather);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/current")
                .param("id", String.valueOf(cityId))
                .param("lang", lang)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cityCode").value(mockWeather.getCityCode()))
                .andExpect(jsonPath("$.temperatureAirC").value(mockWeather.getTemperatureAirC()))
                .andExpect(jsonPath("$.descriptionFull").value(mockWeather.getDescriptionFull()))
                .andDo(MockMvcResultHandlers.print());
        verify(weatherService, times(1)).getCurrentWeather(cityId, lang);
    }

    @Test
    void testGetForecastWeather_Success() throws Exception {
        int cityId = 12345;
        String lang = "en";
        int days = 3;

        when(weatherService.getForecastWeather(cityId, lang, days)).thenReturn(mockWeatherList);

        mockMvc.perform(get("/api/weather/forecast")
                        .param("id", String.valueOf(cityId))
                        .param("lang", lang)
                        .param("days", String.valueOf(days))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cityCode").value(mockWeather.getCityCode()))
                .andExpect(jsonPath("$[0].temperatureAirC").value(mockWeather.getTemperatureAirC()))
                .andExpect(jsonPath("$[0].descriptionFull").value(mockWeather.getDescriptionFull()))
                .andDo(MockMvcResultHandlers.print());
        verify(weatherService).getForecastWeather(cityId, lang, days);
    }
    @Test
    void testSearchCitiesByCoordinates_Success() throws Exception {
        Double latitude = 53.9;
        Double longitude = 27.5667;
        String lang = "en";
        int limit = 5;

        when(citySearchService.searchCitiesByCoordinates(latitude, longitude, lang, limit)).thenReturn(mockCityList);

        mockMvc.perform(get("/api/search/cities/location")
                        .param("latitude", String.valueOf(latitude))
                        .param("longitude", String.valueOf(longitude))
                        .param("lang", lang)
                        .param("limit", String.valueOf(limit))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(mockCity.getId()))
                .andExpect(jsonPath("$[0].name").value(mockCity.getName()))
                .andDo(MockMvcResultHandlers.print());

        verify(citySearchService).searchCitiesByCoordinates(latitude, longitude, lang, limit);
    }
    @Test
    void testSearchCitiesByStr_Success() throws Exception {
        String query = "belgorod";
        String lang = "en";
        int limit = 5;

        when(citySearchService.searchCitiesByStr(query, lang, limit)).thenReturn(mockCityList);

        mockMvc.perform(get("/api/search/cities/string")
                        .param("query", query)
                        .param("lang", lang)
                        .param("limit", String.valueOf(limit))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(mockCity.getId()))
                .andExpect(jsonPath("$[0].name").value(mockCity.getName()))
                .andDo(MockMvcResultHandlers.print());
        verify(citySearchService).searchCitiesByStr(query, lang, limit);
    }


}