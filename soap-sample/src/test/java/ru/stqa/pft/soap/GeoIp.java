package ru.stqa.pft.soap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoIp {
    @JsonProperty("Country")
    private String country;

    @JsonProperty("State")
    private String state;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}