package com.weather.geofence.service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class SessionLocationStore {

    public record StoredLocation(double latitude, double longitude, Instant updatedAt) {}
    public record SessionInfo(String token, Instant createdAt) {}

    private final Map<String, Instant> sessions = new ConcurrentHashMap<>();
    private final Map<String, StoredLocation> locations = new ConcurrentHashMap<>();

    public SessionInfo createSession() {
        String token = UUID.randomUUID().toString();
        Instant now = Instant.now();
        sessions.put(token, now);
        return new SessionInfo(token, now);
    }

    public void saveLocation(String token, double lat, double lon) {
        requireValid(token);
        locations.put(token, new StoredLocation(lat, lon, Instant.now()));        
    }

    public Optional<StoredLocation> getLocation(String token) {
        requireValid(token);
        return Optional.ofNullable(locations.get(token));
    }

    private void requireValid(String token) {
        if (token == null || token.isBlank() || !sessions.containsKey(token)) {
            throw new IllegalArgumentException("Invalid or malformd token");
        }
    }
    
}
