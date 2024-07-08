package ru.tn.nnjack.GismeteoApiService.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tn.nnjack.GismeteoApiService.client.GismeteoClient;
import ru.tn.nnjack.GismeteoApiService.dto.CitiesDto;
import ru.tn.nnjack.GismeteoApiService.models.CityModel;
import ru.tn.nnjack.GismeteoApiService.models.utils.mapper.CityMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CitySearchServiceTest {

    @Mock
    private GismeteoClient gismeteoClient;

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private CitySearchService citySearchService;

    private CitiesDto citiesDto;
    private List<CityModel> cityModelList;

    @BeforeEach
    void setUp() {
        CityModel cityModel = new CityModel();
        cityModel.setId(1);
        cityModel.setName("Test City");

        cityModelList = List.of(cityModel);

        CitiesDto.City cityDto = new CitiesDto.City();
        cityDto.setId(1);
        cityDto.setName("Test City");

        citiesDto = new CitiesDto();
        CitiesDto.ResponseData response = new CitiesDto.ResponseData();
        response.setItems(List.of(cityDto));
        citiesDto.setResponse(response);

        when(cityMapper.cityListToCityModelList(citiesDto.getResponse().getItems())).thenReturn(cityModelList);
    }

    @Test
    void searchCitiesByStr_ShouldReturnCityModelList() {
        when(gismeteoClient.searchCitiesByStr(anyString(), anyString(), anyInt())).thenReturn(citiesDto);

        List<CityModel> result = citySearchService.searchCitiesByStr("test", "en", 5);

        assertEquals(cityModelList, result);
    }

    @Test
    void searchCitiesByCoordinates_ShouldReturnCityModelList() {
        when(gismeteoClient.searchCitiesByCoordinates(anyDouble(), anyDouble(), anyString(), anyInt())).thenReturn(citiesDto);

        List<CityModel> result = citySearchService.searchCitiesByCoordinates(53.9, 27.5667, "en", 5);

        assertEquals(cityModelList, result);
    }

}