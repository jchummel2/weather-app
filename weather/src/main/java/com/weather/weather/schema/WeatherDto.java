package com.weather.weather.schema;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record WeatherDto(
    @JsonIgnore
    double latitude,
    @JsonIgnore
    double longitude,
    String periodName,
    String shortForecast,
    Integer temperature,
    String temperatureUnit,
    String windSpeed,
    String windDirection, 
    Instant fetchedAt
) {}
