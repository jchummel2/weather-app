package com.weather.geofence.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.geofence.schema.LocationDto;
import com.weather.geofence.service.SessionLocationStore;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v4/session")
public class SessionLocationController {

    private final SessionLocationStore store;

    @PostMapping
    public SessionLocationStore.SessionInfo createSession() {
        return store.createSession();
    }

    @PostMapping("/location")
    public void saveMyLocation(
        @RequestHeader("X-Session-Token") String token,
        @RequestBody LocationDto dto
    ) {
        store.saveLocation(token, dto.getLatitude(), dto.getLongitude());
    }


    @GetMapping("/location")
    public SessionLocationStore.StoredLocation getMyLocation(
        @RequestHeader("X-Session-Token") String token 
    ) {
        return store.getLocation(token)
                .orElseThrow(() -> new IllegalArgumentException("No Location Stored yet."));
    }
}
