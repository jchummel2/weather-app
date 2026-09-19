package com.weather.weather.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public record QuantitativeValueDto(
    Integer value,
    String unitText
) {
    @JsonProperty("@type")
    public String jsonLdType() {
        return "QuantitativeValue";
    }
}