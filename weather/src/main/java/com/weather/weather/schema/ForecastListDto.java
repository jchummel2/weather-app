package com.weather.weather.schema;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ForecastListDto (
    @JsonIgnore double latitude,
    @JsonIgnore double longitude,
    @JsonIgnore
    Instant fetchedAt,
    @JsonIgnore List<ForecastDto> periods
) {
    @JsonProperty("@context")
    public String jsonLdContext() {
        return "https://schema.org";
    }

    @JsonProperty("@type")
    public String jsonLdType() {
        return "ItemList";
    }

    @JsonProperty("geo")
    public GeoCoordinatesDto geo() {
        return new GeoCoordinatesDto(latitude, longitude);
    }

    @JsonProperty("dateModified")
    public Instant jsonLdDateModified() {
        return fetchedAt;
    }

    @JsonProperty("itemListElement")
    public List<ForecastDto> jsonLdItems() {
        return periods;
    }
}
