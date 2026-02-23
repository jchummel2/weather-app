package com.weather.weather.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.weather.schema.ForecastDto;
import com.weather.weather.schema.ForecastListDto;
import com.weather.weather.schema.RelativeLocationDto;
import com.weather.weather.service.WeatherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v4/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/current")
    public ForecastDto current(
        @RequestHeader("X-Session-Token") String token) {
        return weatherService.current(token);
    }

    @GetMapping("/forecast")
    public ForecastListDto forecast(
        @RequestHeader("X-Session-Token") String token) {
            return weatherService.forecast(token);
    }

    @GetMapping("/location")
    public RelativeLocationDto location(
        @RequestHeader("X-Session-Token") String token) {
        return weatherService.location(token);
    }
    
}
