package com.travel.service;

import com.travel.entity.City;
import com.travel.repo.CityRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CityServiceTest {
    @Autowired
    private CityService cityService;

    @MockBean
    private CityRepo cityRepo;

    @Test
    public void addCity() {
        City city = new City(100L, "Москва", "Кремль");
        cityService.addCity(city);
        verify(cityRepo, times(1)).save(city);
    }

    @Test
    public void getAllCities() {
        when(cityRepo.findAll()).thenReturn(Stream
                .of(new City(100L, "Москва", "Кремль"), new City(101L, "Прага", "Карлов мост")).collect(Collectors.toList()));
        assertEquals(2, cityService.getAllCities().size());
    }

    @Test
    public void getCityById() {
        Long cityId = 100L;
        City city = new City(cityId, "Москва", "Кремль");
        when(cityRepo.findById(cityId)).thenReturn(Stream
                .of(city).findFirst());
        assertEquals(city, cityService.getCityById(cityId));
    }

    @Test
    public void getCityByName() {
        String cityName = "Москва";
        when(cityRepo.findCitiesByName(cityName)).thenReturn(Stream
                .of(new City(100L, cityName, "Кремль")).collect(Collectors.toList()));
        assertEquals(1, cityService.getCityByName(cityName).size());
    }

    @Test
    public void deleteById() {
        City city = new City(100L, "Москва", "Кремль");
        cityService.deleteCityById(100L);
        verify(cityRepo, times(1)).deleteById(100L);
    }
}