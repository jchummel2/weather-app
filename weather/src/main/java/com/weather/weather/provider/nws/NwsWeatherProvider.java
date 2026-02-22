package com.weather.weather.provider.nws;

import java.time.Instant;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.weather.weather.provider.WeatherProvider;
import com.weather.weather.schema.WeatherDto;

@Component
public class NwsWeatherProvider implements WeatherProvider {

    private final RestClient restClient;

    public NwsWeatherProvider(RestClient.Builder builder) {
        this.restClient = builder
            .defaultHeader(HttpHeaders.USER_AGENT, "weather-api (guest@guest.com)")
            .build();
    }

    @Override
    public WeatherDto current(double latitude, double longitude) {

        double lat = round4(latitude);
        double lon = round4(longitude);

        var points = restClient.get()
            .uri("https://api.weather.gov/points/{lat},{lon}", lat, lon)
            .retrieve()
            .body(NwsModels.PointsResponse.class);

        if (points == null || points.properties() == null ) {
            throw new IllegalStateException("Invalid response from NWS");
        }
        String forecastUrl = points.properties().forecast();
        var forecast = restClient.get()
                .uri(forecastUrl)
                .retrieve()
                .body(NwsModels.ForecastResponse.class);
        if (forecast == null
            || forecast.properties() == null
            || forecast.properties().periods().isEmpty()
        ) {
            throw new IllegalStateException("Invalid forecast response from NWS");
        }

        var period = forecast.properties().periods().getFirst();
        
        return new WeatherDto(
            lat,
            lon,
            period.name(),
            period.shortForecast(),
            period.temperature(),
            period.temperatureUnit(),
            period.windSpeed(),
            period.windDirection(),
            Instant.now()
        );
    }

    private double round4(double value) {
        return Math.round(value * 10_000d) / 10_000d;
    }
    
}
