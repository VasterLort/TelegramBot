package com.travel.controller;

import com.travel.entity.City;
import com.travel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/cities")
public class CityController {
    Logger logger = Logger.getLogger("CityController");

    @Autowired
    private CityService cityService;

    @GetMapping
    public String getAllCities(Map<String, Object> model) {
        model.put("cities", cityService.getAllCities());
        return "city";
    }

    @PostMapping
    public String addCity(@RequestParam String cityName, Map<String, Object> model) {
        cityService.addCity(new City(cityName));
        model.put("cities", cityService.getAllCities());
        return "city";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String cityName, Map<String, Object> model) {
        model.put("cities", cityService.getCityByName(cityName));
        return "city";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Map<String, Object> model) {
        cityService.deleteCityById(id);
        model.put("cities", cityService.getAllCities());
        return "city";
    }

    @GetMapping("/{id}/info")
    public String getCityInfo(@PathVariable Long id, Map<String, Object> model) {
        model.put("city", cityService.getCityById(id));
        return "info";
    }

    @PostMapping("/{id}/info/edit")
    public String updateCityInfo(@PathVariable("id") Long id, @RequestParam(required = false) String info, Map<String, Object> model) {
        model.put("city", cityService.updateCityInfo(id, info));
        return "info";
    }
}
