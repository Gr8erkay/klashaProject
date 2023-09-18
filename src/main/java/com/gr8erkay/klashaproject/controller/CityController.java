package com.gr8erkay.klashaproject.controller;

import com.gr8erkay.klashaproject.entity.City;
import com.gr8erkay.klashaproject.entity.Country;
import com.gr8erkay.klashaproject.model.responseDTO.CountryResponse;
import com.gr8erkay.klashaproject.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/most-populated")
    public List<City> getMostPopulatedCities(@RequestParam int N) {
        return cityService.getMostPopulatedCities(N);
    }

    @GetMapping("/country-info")
    public CountryResponse getCountryInfo(@RequestParam String country) {
        return cityService.getCountryInfo(country);
    }
}
