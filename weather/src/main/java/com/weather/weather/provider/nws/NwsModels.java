package com.weather.weather.provider.nws;

import java.util.List;

public class NwsModels {
    
    private NwsModels() {}

    public record PointsResponse(Properties properties) {
        public record Properties(String forecast, String forecastHourly) {}
    }

    public record ForecastResponse(Properties properties) {
        public record Properties(List<Period> periods) {}
    }

    public record Period(
        String name,
        Integer temperature,
        String temperatureUnit,
        String windSpeed,
        String windDirection,
        String shortForecast
    ) {}
}
