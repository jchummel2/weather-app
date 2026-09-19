package com.weather.weather.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostalAddressDto(
    String addressLocality,
    String addressRegion
) {
    @JsonProperty("@type")
    public String jsonLdType() {
        return "PostalAddress";
    }
}