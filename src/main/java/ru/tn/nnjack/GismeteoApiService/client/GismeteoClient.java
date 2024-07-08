package ru.tn.nnjack.GismeteoApiService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tn.nnjack.GismeteoApiService.dto.CitiesDto;
import ru.tn.nnjack.GismeteoApiService.dto.CurrentWeatherDTO;
import ru.tn.nnjack.GismeteoApiService.dto.WeatherForecastDTO;


@FeignClient(name = "gismeteoClient", url = "${gismeteo.base-url}", configuration = FeignConfig.class)
public interface GismeteoClient {
    @GetMapping("/v2/weather/current/{id}/")
    CurrentWeatherDTO getCurrentWeather(
            @PathVariable("id") int id,
            @RequestParam("lang") String lang);

    @GetMapping(value = "/v2/search/cities/", params = {"lang", "query", "limit"})
    CitiesDto searchCitiesByStr(
            @RequestParam("lang") String lang,
            @RequestParam("query") String query,
            @RequestParam("limit") int limit);

    @GetMapping(value = "/v2/search/cities/", params = {"latitude","longitude","lang", "limit"})
    CitiesDto searchCitiesByCoordinates(
            @RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude,
            @RequestParam("lang") String lang,
            @RequestParam("limit") int limit);

    @GetMapping("/v2/weather/forecast/{id}/")
    WeatherForecastDTO getForecastWeather(
            @PathVariable("id") int id,
            @RequestParam("days") int days,
            @RequestParam("lang") String lang);
}