package com.weather.weather.schema;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record ForecastListDto (
    @JsonIgnore double latitude,
    @JsonIgnore double longitude,
    Instant fetchedAt,
    List<ForecastDto> periods
) {}
