package ru.tn.nnjack.GismeteoApiService.models.utils.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tn.nnjack.GismeteoApiService.dto.WeatherDTO;
import ru.tn.nnjack.GismeteoApiService.models.WeatherModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class WeatherMapperTest {

    @Autowired
    private WeatherMapper weatherMapper;

    @Test
    void testWeatherDtoToModel() {
        // Создаем тестовый WeatherDTO
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setDate(new WeatherDTO.DateInfo());
        weatherDTO.getDate().setUnix(1625509200L);
        weatherDTO.setIcon("clear-sky");
        weatherDTO.setDescription(new WeatherDTO.DescriptionInfo());
        weatherDTO.getDescription().setFull("Clear sky");
        weatherDTO.setCity(12345);
        weatherDTO.setTemperature(new WeatherDTO.TemperatureInfo());
        weatherDTO.getTemperature().setAir(new WeatherDTO.TemperatureInfo.Temperature());
        weatherDTO.getTemperature().getAir().setC(25.0);
        weatherDTO.setPrecipitation(new WeatherDTO.PrecipitationInfo());
        weatherDTO.getPrecipitation().setAmount(0.0);
        weatherDTO.setWind(new WeatherDTO.WindInfo());
        weatherDTO.getWind().setDirection(new WeatherDTO.WindInfo.Direction());
        weatherDTO.getWind().setSpeed(new WeatherDTO.WindInfo.Speed());
        weatherDTO.getWind().getSpeed().setM_s(5);

        // Вызываем метод маппера
        WeatherModel weatherModel = weatherMapper.weatherDtoToModel(weatherDTO);

        // Проверяем соответствие маппинга
        assertEquals(1625509200L, weatherModel.getDateUnix());
        assertEquals("clear-sky", weatherModel.getIcon());
        assertEquals("Clear sky", weatherModel.getDescriptionFull());
        assertEquals(12345, weatherModel.getCityCode());
        assertEquals(25.0, weatherModel.getTemperatureAirC());
        assertEquals(0.0, weatherModel.getPrecipitationAmount());
        assertEquals(5, weatherModel.getWindSpeedMS());
    }
}
