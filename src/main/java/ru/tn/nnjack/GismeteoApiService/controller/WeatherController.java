package ru.tn.nnjack.GismeteoApiService.controller;



import org.springframework.web.bind.annotation.*;
import ru.tn.nnjack.GismeteoApiService.models.CityModel;
import ru.tn.nnjack.GismeteoApiService.models.WeatherModel;
import ru.tn.nnjack.GismeteoApiService.service.CitySearchService;
import ru.tn.nnjack.GismeteoApiService.service.WeatherService;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;


@RestController
@RequestMapping("/api")
@Tag(name = "Weather", description = "API для получения информации о погоде")
public class WeatherController {

    private final WeatherService weatherService;
    private final CitySearchService citySearchService;

    public WeatherController(WeatherService weatherService, CitySearchService citySearchService) {
        this.weatherService = weatherService;
        this.citySearchService = citySearchService;
    }

    @GetMapping("/weather/current")
    @Operation(summary = "Получить текущую погоду",
            description = "Получить текущую погоду по идентификатору географического объекта и языку",
            responses = {
                    @ApiResponse(description = "Успешный ответ",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = WeatherModel.class)))
            })
    public WeatherModel getCurrentWeather(
            @Parameter(description = "Идентификатор географического объекта", required = true) @RequestParam("id") int id,
            @Parameter(description = "Язык ответа") @RequestParam(value = "lang", defaultValue = "ru") String lang) {
        return weatherService.getCurrentWeather(id, lang);
    }

    @GetMapping("/search/cities/string")
    @Operation(summary = "Поиск географических объектов по строке",
            description = "Поиск географических объектов по строковому запросу, с указанием языка и лимита результатов",
            responses = {
                    @ApiResponse(description = "Успешный ответ",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CityModel.class))))
            })
    public List<CityModel> searchCitiesByStr(
            @Parameter(description = "Строковый запрос для поиска географических объектов", required = true) @RequestParam("query") String query,
            @Parameter(description = "Язык ответа") @RequestParam(value = "lang", defaultValue = "en") String lang,
            @Parameter(description = "Лимит количества результатов") @RequestParam(value="limit", defaultValue = "5") int limit) {
        return citySearchService.searchCitiesByStr(query, lang, limit);
    }

    @GetMapping("/search/cities/location")
    @Operation(summary = "Поиск географических объектов по координатам",
            description = "Поиск географических объектов по координатам (широта и долгота), с указанием языка и лимита результатов",
            responses = {
                    @ApiResponse(description = "Успешный ответ",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CityModel.class))))
            })
    public List<CityModel> searchCitiesByCoordinates(
            @Parameter(description = "Широта", required = true) @RequestParam("latitude") Double latitude,
            @Parameter(description = "Долгота", required = true) @RequestParam("longitude") Double longitude,
            @Parameter(description = "Язык ответа") @RequestParam(value = "lang", defaultValue = "en") String lang,
            @Parameter(description = "Лимит количества результатов") @RequestParam(value="limit", defaultValue = "5") int limit) {
        return citySearchService.searchCitiesByCoordinates(latitude, longitude, lang, limit);
    }

    @GetMapping("/weather/forecast")
    @Operation(summary = "Получить прогноз погоды",
            description = "Получить прогноз погоды по идентификатору географического объекта, языку и количеству дней",
            responses = {
                    @ApiResponse(description = "Успешный ответ",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = WeatherModel.class))))
            })
    public List<WeatherModel> getForecastWeather(
            @Parameter(description = "Идентификатор географического объекта", required = true) @RequestParam("id") int id,
            @Parameter(description = "Язык ответа") @RequestParam(value = "lang", defaultValue = "en") String lang,
            @Parameter(description = "Количество дней прогноза") @RequestParam(value = "days", defaultValue = "1") int days) {
        return weatherService.getForecastWeather(id, lang, days);
    }
}
