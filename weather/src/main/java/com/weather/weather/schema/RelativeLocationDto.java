package com.weather.weather.schema;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;

public record RelativeLocationDto (
    @JsonIgnore
    @Nonnull
    double lat,
    @Nonnull
    @JsonIgnore
    double lon,
    String city,
    String state,
    Instant fetchedAt
) {}
