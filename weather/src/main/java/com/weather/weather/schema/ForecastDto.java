package com.weather.weather.schema;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ForecastDto(
    @JsonIgnore
    double latitude,
    @JsonIgnore
    double longitude,
    @JsonIgnore
    String periodName,
    @JsonIgnore
    String shortForecast,
    @JsonIgnore
    Integer temperature,
    @JsonIgnore
    String temperatureUnit,
    @JsonIgnore
    String windSpeed,
    @JsonIgnore
    String windDirection, 
    @JsonIgnore
    Instant fetchedAt
) {
    @JsonProperty("@context")
    public String jsonLdContext() {
        return "https://schema.org";
    }

    @JsonProperty("@type")
    public String jsonLdType() {
        return "WeatherForecast";
    }

    @JsonProperty("geo")
    public GeoCoordinatesDto geo() {
        return new GeoCoordinatesDto(latitude, longitude);
    }

    @JsonProperty("name")
    public String jsonLdName() {
        return periodName;
    }

    @JsonProperty("description")
    public String jsonLdDescription() {
        return shortForecast;
    }

    @JsonProperty("temperature")
    public QuantitativeValueDto jsonLdTemperature() {
        return new QuantitativeValueDto(temperature, temperatureUnit);
    }

    @JsonProperty("windSpeed")
    public String jsonLdWindSpeed() {
        return windSpeed;
    }

    @JsonProperty("windDirection")
    public String jsonLdWindDirection() {
        return windDirection;
    }

    @JsonProperty("dateModified")
    public Instant jsonLdDateModified() {
        return fetchedAt;
    }
}
