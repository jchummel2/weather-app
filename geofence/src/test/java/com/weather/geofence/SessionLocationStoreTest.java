package com.weather.geofence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.weather.geofence.service.SessionLocationStore;

class SessionLocationStoreTest {
    @Test
    void storesValidWindowsLocationCoordinates() {
        SessionLocationStore store = new SessionLocationStore();
        String token = store.createSession().token();

        assertDoesNotThrow(() -> store.saveLocation(token, 37.7749, -122.4194));
    }

    @Test
    void rejectsInvalidCoordinates() {
        SessionLocationStore store = new SessionLocationStore();
        String token = store.createSession().token();

        assertThrows(IllegalArgumentException.class, () -> store.saveLocation(token, 91, 0));
        assertThrows(IllegalArgumentException.class, () -> store.saveLocation(token, 0, 181));
    }
}