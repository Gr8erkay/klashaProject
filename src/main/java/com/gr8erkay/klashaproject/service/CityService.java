package com.gr8erkay.klashaproject.service;

import com.gr8erkay.klashaproject.entity.City;
import com.gr8erkay.klashaproject.entity.Country;
import com.gr8erkay.klashaproject.model.responseDTO.CountryResponse;

import java.util.List;

public interface CityService {

    City getCityInfo(String cityName);

    List<City> getMostPopulatedCities(int N);
    CountryResponse getCountryInfo(String country);

}
