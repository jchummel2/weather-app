package com.weather.weather.provider;

import com.weather.weather.schema.WeatherDto;

public interface WeatherProvider {
    WeatherDto current(double latitude, double longitude);
}