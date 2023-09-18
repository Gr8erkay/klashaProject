package com.gr8erkay.klashaproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    private long population;
    private String capitalCity;
    private String location;
    private String currency;
    private String iso2;
    private String iso3;


}
