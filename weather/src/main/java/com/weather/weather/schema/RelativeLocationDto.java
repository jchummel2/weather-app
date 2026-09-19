package com.weather.weather.schema;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nonnull;

public record RelativeLocationDto (
    @JsonIgnore
    @Nonnull
    double lat,
    @Nonnull
    @JsonIgnore
    double lon,
    @JsonIgnore
    String city,
    @JsonIgnore
    String state,
    @JsonIgnore
    Instant fetchedAt
) {
    @JsonProperty("@context")
    public String jsonLdContext() {
        return "https://schema.org";
    }

    @JsonProperty("@type")
    public String jsonLdType() {
        return "Place";
    }

    @JsonProperty("geo")
    public GeoCoordinatesDto geo() {
        return new GeoCoordinatesDto(lat, lon);
    }

    @JsonProperty("name")
    public String jsonLdName() {
        return city + ", " + state;
    }

    @JsonProperty("address")
    public PostalAddressDto address() {
        return new PostalAddressDto(city, state);
    }

    @JsonProperty("dateModified")
    public Instant jsonLdDateModified() {
        return fetchedAt;
    }
}
