package com.weather.weather.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GeoCoordinatesDto(
    double latitude,
    double longitude
) {
    @JsonProperty("@type")
    public String jsonLdType() {
        return "GeoCoordinates";
    }
}