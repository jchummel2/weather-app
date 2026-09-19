package com.weather.weather.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

class JsonLdSerializationTest {

    private final JsonMapper objectMapper = JsonMapper.builder().build();

    @Test
    void serializesForecastAsSchemaOrgJsonLd() throws Exception {
        var forecast = new ForecastDto(
            38.8977,
            -77.0365,
            "Tonight",
            "Mostly clear",
            18,
            "F",
            "5 mph",
            "NW",
            Instant.parse("2026-09-19T12:00:00Z")
        );

        JsonNode json = objectMapper.readTree(objectMapper.writeValueAsString(forecast));

        assertEquals("https://schema.org", json.get("@context").asText());
        assertEquals("WeatherForecast", json.get("@type").asText());
        assertEquals("Tonight", json.get("name").asText());
        assertEquals(38.8977, json.at("/geo/latitude").asDouble());
        assertEquals("QuantitativeValue", json.at("/temperature/@type").asText());
        assertFalse(json.has("latitude"));
        assertFalse(json.has("periodName"));
    }

    @Test
    void serializesForecastListAsItemList() throws Exception {
        var period = new ForecastDto(
            38.8977,
            -77.0365,
            "Tonight",
            "Mostly clear",
            18,
            "F",
            "5 mph",
            "NW",
            Instant.parse("2026-09-19T12:00:00Z")
        );
        var forecast = new ForecastListDto(
            38.8977,
            -77.0365,
            Instant.parse("2026-09-19T12:00:00Z"),
            List.of(period)
        );

        JsonNode json = objectMapper.readTree(objectMapper.writeValueAsString(forecast));

        assertEquals("ItemList", json.get("@type").asText());
        assertTrue(json.get("itemListElement").isArray());
        assertEquals("WeatherForecast", json.at("/itemListElement/0/@type").asText());
        assertFalse(json.has("periods"));
    }
}