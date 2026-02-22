package com.weather.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.weather.config"})
public class WeatherAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherAppApplication.class, args);
    }

}
