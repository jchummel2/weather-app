package com.weather.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.weather.weather"})
public class WeatherConfig {
    
}
