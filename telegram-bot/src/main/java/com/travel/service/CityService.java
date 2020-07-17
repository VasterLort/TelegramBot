package com.travel.service;

import com.travel.entity.City;
import com.travel.repo.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CityService {
    private Logger logger = Logger.getLogger("CityService");
    private final static String CITY_DOES_NOT_EXIST = "Такого города не существует";
    private final static String CITY_INFO_DOES_NOT_EXIST = "Нет информации об этом городе";

    @Autowired
    private CityRepo cityRepo;

    public void addCity(City city) {
        if (!cityRepo.existsByName(city.getName()) && !city.getName().isBlank() && city.getName() != null) {
            cityRepo.save(city);
        }
    }

    public List<City> getAllCities() {
        return cityRepo.findAll();
    }

    public City getCityById(Long id) {
        return cityRepo.findById(id).orElse(null);
    }

    public List<City> getCityByName(String name) {
        City city = cityRepo.findCityByName(name).orElse(null);
        if (city == null) {
            return cityRepo.findAll();
        } else return cityRepo.findCitiesByName(name);
    }

    public String getCityInfo(String cityName) {
        City city = cityRepo.findCityByName(cityName).orElse(null);
        if (city == null) {
            return CITY_DOES_NOT_EXIST;
        } else if (city.getInfo() == null || city.getInfo().isEmpty()) {
            return CITY_INFO_DOES_NOT_EXIST;
        } else return city.getInfo();
    }

    public City updateCityInfo(Long id, String info) {
        City city = cityRepo.findById(id).orElse(null);
        city.setInfo(info);
        return cityRepo.save(city);
    }

    public void deleteCityById(Long id) {
        cityRepo.deleteById(id);
    }
}
