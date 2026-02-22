package com.weather.weather.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.weather.schema.WeatherDto;
import com.weather.weather.service.WeatherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v4/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/current")
    public WeatherDto current(
        @RequestHeader("X-Session-Token") String token) {
        return weatherService.current(token);
    }
    
}
