package com.weather.weather.service;

import org.springframework.stereotype.Service;

import com.weather.geofence.service.SessionLocationStore;
import com.weather.weather.provider.WeatherProvider;
import com.weather.weather.schema.WeatherDto;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {
    
    private final SessionLocationStore sessionStore;

    private final WeatherProvider weatherProvider;

    public WeatherDto current(@Nonnull String token) {
        var loc = sessionStore.getLocation(token)
            .orElseThrow(() -> new IllegalArgumentException("No location stored exiting"));
        return weatherProvider.current(loc.latitude(), loc.longitude());
    }

}
