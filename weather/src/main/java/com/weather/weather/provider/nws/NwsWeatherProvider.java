package com.weather.weather.provider.nws;

import java.time.Instant;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.weather.weather.mapper.ForecastMapper;
import com.weather.weather.provider.WeatherProvider;
import com.weather.weather.schema.ForecastDto;
import com.weather.weather.schema.ForecastListDto;
import com.weather.weather.schema.RelativeLocationDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NwsWeatherProvider implements WeatherProvider {

    private final RestClient restClient;
    private final ForecastMapper forecastMapper;

    public NwsWeatherProvider(RestClient.Builder builder, ForecastMapper forecastMapper) {
        this.restClient = builder
            .defaultHeader(HttpHeaders.USER_AGENT, "weather-api (guest@guest.com)")
            .build();
        this.forecastMapper = forecastMapper;
    }

    @Override
    public ForecastDto current(double latitude, double longitude) {
        double lat = round4(latitude);
        double lon = round4(longitude);

        var forecast = ForecastResponse(lat, lon);

        var period = forecast.properties().periods().getFirst();
        
        return new ForecastDto(
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

    @Override
    public ForecastListDto forecast(double latitude, double longitude) {
        double lat = round4(latitude);
        double lon = round4(longitude);
        
        var forecast = ForecastResponse(lat, lon);

        var nwsPeriods = forecast.properties().periods();

        int fromIndex = Math.min(1, nwsPeriods.size());
        int toIndex = Math.min(7, nwsPeriods.size());

        var wholeWeek = nwsPeriods.subList(fromIndex, toIndex);

        var mapped = forecastMapper.toList(wholeWeek);

        var finalList = mapped.stream()
            .map(p -> new ForecastDto(
                lat,
                lon,
                p.periodName(),
                p.shortForecast(),
                p.temperature(),
                p.temperatureUnit(),
                p.windSpeed(),
                p.windDirection(),
                Instant.now()
            ))
            .toList();

    return new ForecastListDto(
        lat,
        lon,
        Instant.now(),
        finalList
    );
    }

    @Override
    public RelativeLocationDto location(double latitude, double longitude) {
        throw new UnsupportedOperationException("This doesn't work yet");
    }


    private double round4(double value) {
        return Math.round(value * 10_000d) / 10_000d;
    }


    private NwsModels.ForecastResponse ForecastResponse(double lat, double lon) {
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
        
        return forecast;
    }
}
