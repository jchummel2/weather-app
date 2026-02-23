package com.weather.weather.service;

import org.springframework.stereotype.Service;
import com.weather.geofence.service.SessionLocationStore;
import com.weather.weather.provider.WeatherProvider;
import com.weather.weather.schema.ForecastDto;
import com.weather.weather.schema.ForecastListDto;
import com.weather.weather.schema.RelativeLocationDto;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {
    
    private final SessionLocationStore sessionStore;

    private final WeatherProvider weatherProvider;

    public ForecastDto current(@Nonnull String token) {
        var loc = sessionStore.getLocation(token)
            .orElseThrow(() -> new IllegalArgumentException("No location stored exiting"));
        return weatherProvider.current(loc.latitude(), loc.longitude());
    }

    public ForecastListDto forecast(@Nonnull String token) {
        var loc = sessionStore.getLocation(token)
            .orElseThrow(() -> new IllegalArgumentException("No location stored exiting"));
        return weatherProvider.forecast(loc.latitude(), loc.longitude());
    }

    public RelativeLocationDto location(@Nonnull String token) {
        var loc = sessionStore.getLocation(token)
            .orElseThrow(() -> new IllegalArgumentException("No location stored exiting"));
        return weatherProvider.location(loc.latitude(), loc.longitude());
    }

}
