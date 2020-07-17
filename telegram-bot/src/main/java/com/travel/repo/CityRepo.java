package com.travel.repo;

import com.travel.entity.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepo extends CrudRepository<City, Long> {
    List<City> findCitiesByName(String name);

    List<City> findAll();

    boolean existsByName(String name);

    Optional<City> findCityByName(String name);
}
