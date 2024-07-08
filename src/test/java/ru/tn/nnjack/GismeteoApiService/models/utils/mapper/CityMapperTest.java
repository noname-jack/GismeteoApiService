package ru.tn.nnjack.GismeteoApiService.models.utils.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tn.nnjack.GismeteoApiService.dto.CitiesDto;
import ru.tn.nnjack.GismeteoApiService.models.CityModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class CityMapperTest {

    @Autowired
    CityMapper cityMapper;

    @Test
    void testCityDtoToCityModel() {
        // Создаем тестовый объект CitiesDto.City
        CitiesDto.City cityDto = new CitiesDto.City();
        cityDto.setId(1);
        cityDto.setName("Test City");
        cityDto.setCountry(new CitiesDto.Country());
        cityDto.getCountry().setCode("ru");
        cityDto.setDistrict(new CitiesDto.District());
        cityDto.getDistrict().setName("Test District");
        cityDto.setSubDistrict(new CitiesDto.SubDistrict());
        cityDto.getSubDistrict().setName("Test SubDistrict");

        // Вызываем маппер для преобразования CitiesDto.City в CityModel
        CityModel cityModel = cityMapper.cityDtoToCityModel(cityDto);

        // Проверяем, что поля были правильно отображены
        Assertions.assertEquals(cityDto.getId(), cityModel.getId());
        Assertions.assertEquals(cityDto.getName(), cityModel.getName());
        Assertions.assertEquals(cityDto.getKind(), cityModel.getKind());
        Assertions.assertEquals(cityDto.getCountry().getCode(), cityModel.getCountry());
        Assertions.assertEquals(cityDto.getDistrict().getName(), cityModel.getDistrict());
        Assertions.assertEquals(cityDto.getSubDistrict().getName(), cityModel.getSubDistrict());
    }

    @Test
    void testCityListToCityModelList() {
        // Создаем список тестовых объектов CitiesDto.City
        CitiesDto.City cityDto1 = new CitiesDto.City();
        cityDto1.setId(1);
        cityDto1.setName("City 1");

        CitiesDto.City cityDto2 = new CitiesDto.City();
        cityDto2.setId(2);
        cityDto2.setName("City 2");

        List<CitiesDto.City> cityDtoList = Arrays.asList(cityDto1, cityDto2);

        // Вызываем маппер для преобразования списка CitiesDto.City в список CityModel
        List<CityModel> cityModelList = cityMapper.cityListToCityModelList(cityDtoList);

        // Проверяем размер списка и соответствие каждого элемента
        Assertions.assertEquals(cityDtoList.size(), cityModelList.size());

        for (int i = 0; i < cityDtoList.size(); i++) {
            Assertions.assertEquals(cityDtoList.get(i).getId(), cityModelList.get(i).getId());
            Assertions.assertEquals(cityDtoList.get(i).getName(), cityModelList.get(i).getName());
        }
    }
}
