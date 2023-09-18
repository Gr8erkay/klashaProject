package com.gr8erkay.klashaproject.model.responseDTO;

import java.util.ArrayList;

public class CountryResponse {
        private boolean error;
        private String msg;
        Data DataObject;




    public class Data {
        private String country;
        private String code;
        private String iso3;
        ArrayList< Object > populationCounts = new ArrayList < Object > ();



        public String getCountry() {
            return country;
        }

        public String getCode() {
            return code;
        }

        public String getIso3() {
            return iso3;
        }

        // Setter Methods

        public void setCountry(String country) {
            this.country = country;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setIso3(String iso3) {
            this.iso3 = iso3;
        }
    }
}
