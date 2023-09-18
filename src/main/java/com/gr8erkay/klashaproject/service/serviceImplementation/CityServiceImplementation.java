package com.gr8erkay.klashaproject.service.serviceImplementation;

import com.gr8erkay.klashaproject.entity.City;
import com.gr8erkay.klashaproject.entity.Country;
import com.gr8erkay.klashaproject.model.requestDTO.CountryRequest;
import com.gr8erkay.klashaproject.model.responseDTO.CountryResponse;
import com.gr8erkay.klashaproject.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImplementation implements CityService {

    @Value("${external.api.url}")
    private String apiUrl; // Set this in application.properties or application.yml

    private final WebClient webClient;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public CityServiceImplementation(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(apiUrl).build();
    }
    @Override
    public City getCityInfo(String cityName) {
        Mono<City> responseMono = webClient.get()
                .uri("/countries/population/cities")
                .retrieve()
                .bodyToMono(City.class);

        City cityInfo = responseMono.block(); // Blocking call, consider using reactive programming for non-blocking

        return cityInfo;// Handle error or no data case accordingly
    }

    @Override
    public List<City> getMostPopulatedCities(int N) {
        List<City> cities = getCitiesFromApi();

        // Sort cities by population in descending order
        cities.sort(Comparator.comparingInt(c -> Integer.parseInt(c.getPopulationCounts().toString())));

        // Return the first N cities or all if there are fewer
        return cities.stream().limit(N).collect(Collectors.toList());
    }

    @Override
    public CountryResponse getCountryInfo(String country) {
        CountryRequest countryRequest = new CountryRequest();
        countryRequest.setCountry(country);
        Mono<CountryResponse> responseMono = webClientBuilder.build()
                .post()
                .uri(apiUrl + "/countries/population/country")
                .body(Mono.just(countryRequest), CountryRequest.class)
                .retrieve()
                .bodyToMono(CountryResponse.class);

        return responseMono.block();
    }

    private List<City> getCitiesFromApi() {
        Mono<City[]> responseMono = webClientBuilder.build()
                .get()
                .uri(apiUrl + "/countries/population/cities")
                .retrieve()
                .bodyToMono(City[].class);

        City[] citiesArray = responseMono.block();
        return Arrays.asList(citiesArray);
    }

}
