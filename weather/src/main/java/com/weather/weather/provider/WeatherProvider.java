package com.weather.weather.provider;

import com.weather.weather.schema.ForecastDto;
import com.weather.weather.schema.ForecastListDto;
import com.weather.weather.schema.RelativeLocationDto;

public interface WeatherProvider {
    ForecastDto current(double latitude, double longitude);
    ForecastListDto forecast(double latitude, double longitude);
    RelativeLocationDto location(double latitude, double longitude);
}