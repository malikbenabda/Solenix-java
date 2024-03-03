package com.solenix.interview.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solenix.interview.model.LatLongCoord;
import com.solenix.interview.model.Position;
import com.solenix.interview.model.SpacecraftEvent;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.*;

@Service
public class DataServiceImpl implements DataService {

    private  List<SpacecraftEvent> events;
    private  List<Position> latitudes;
    private  List<Position> longitudes;

    public DataServiceImpl() {
        // Load data from JSON files
        this.events = loadData("./static/events.json", new TypeReference<List<SpacecraftEvent>>() {});
        this.latitudes = loadData("./static/latitudes.json", new TypeReference<List<Position>>() {});
        this.longitudes = loadData("./static/longitudes.json", new TypeReference<List<Position>>() {});
    }

    @Override
    public List<SpacecraftEvent> getAllEventsWithPositions() {
        List<SpacecraftEvent> eventsWithPositions = new ArrayList<>();
        for (SpacecraftEvent event : events) {
            double latitude = findClosestPosition(latitudes, event.getOccurrenceTime());
            double longitude = findClosestPosition(longitudes, event.getOccurrenceTime());
            event.setLatitudePosition(latitude);
            event.setLongitudePosition(longitude);
            eventsWithPositions.add(event);
        }
        return eventsWithPositions;
    }

    @Override
    public LatLongCoord getEventWithPosition(String id) {
        Optional<SpacecraftEvent> optionalEvent = events.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
        if (optionalEvent.isPresent()) {
            SpacecraftEvent event = optionalEvent.get();
            double latitude = findClosestPosition(latitudes, event.getOccurrenceTime());
            double longitude = findClosestPosition(longitudes, event.getOccurrenceTime());
            return new LatLongCoord(latitude, longitude);
        }
        return null;
    }

    private double findClosestPosition(List<Position> positions, Date occurrenceTime) {
        Optional<Position> optionalPosition = positions.stream()
                .min(Comparator.comparingLong(p -> Math.abs(Duration.between(p.getTimestamp().toInstant(), occurrenceTime.toInstant()).toMillis())));
        return optionalPosition.map(Position::getPosition).orElse(0.0);
    }
    private <T> List<T> loadData(String fileName, TypeReference<List<T>> typeReference) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream != null) {
                return objectMapper.readValue(inputStream, typeReference);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
