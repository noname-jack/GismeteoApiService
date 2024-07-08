package ru.tn.nnjack.GismeteoApiService.service;

import org.springframework.stereotype.Service;
import ru.tn.nnjack.GismeteoApiService.client.GismeteoClient;
import ru.tn.nnjack.GismeteoApiService.dto.CitiesDto;
import ru.tn.nnjack.GismeteoApiService.models.CityModel;
import ru.tn.nnjack.GismeteoApiService.models.utils.mapper.CityMapper;

import java.util.List;

@Service

public class CitySearchService {
    private final GismeteoClient gismeteoClient;
    private final CityMapper cityMapper;

    public CitySearchService(GismeteoClient gismeteoClient, CityMapper cityMapper) {
        this.gismeteoClient = gismeteoClient;
        this.cityMapper = cityMapper;
    }

    public List<CityModel> searchCitiesByStr(String query, String lang, int limit){
        CitiesDto citiesDto = gismeteoClient.searchCitiesByStr(lang,query,limit);
        List<CityModel> cityModelList = cityMapper.cityListToCityModelList(citiesDto.getResponse().getItems());
        for(CityModel cityModel : cityModelList){
            cityModel.setKind(cityModel.getKind(),lang);
        }
        return cityModelList;
    }

    public List<CityModel> searchCitiesByCoordinates(Double latitude, Double longitude, String lang, int limit){
        CitiesDto citiesDto = gismeteoClient.searchCitiesByCoordinates(latitude,longitude,lang,limit);
        List<CityModel> cityModelList = cityMapper.cityListToCityModelList(citiesDto.getResponse().getItems());
        for(CityModel cityModel : cityModelList){
            cityModel.setKind(cityModel.getKind(),lang);
        }
        return cityModelList;
    }
}
