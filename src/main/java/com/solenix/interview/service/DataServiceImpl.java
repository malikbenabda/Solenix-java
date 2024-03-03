package com.solenix.interview.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.solenix.interview.model.LatLongCoord;
import com.solenix.interview.model.Position;
import com.solenix.interview.model.SpacecraftEvent;
import com.solenix.interview.service.utils.FileUtility;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataServiceImpl implements DataService {

    private final List<SpacecraftEvent> events;
    private final List<Position> latitudes;
    private final List<Position> longitudes;

    public DataServiceImpl() {
        // Load data from JSON files
        this.events = FileUtility.loadData("./static/events.json", new TypeReference<>() {});
        this.latitudes = FileUtility.loadData("./static/latitudes.json", new TypeReference<>() {});
        this.longitudes = FileUtility.loadData("./static/longitudes.json", new TypeReference<>() {});
    }

    @Override
    public List<SpacecraftEvent> getAllEventsWithPositions() {
        List<SpacecraftEvent> eventsWithPositions = new ArrayList<>();
        for (SpacecraftEvent event : events) {
            double latitude = FileUtility.findClosestPosition(latitudes, event.getOccurrenceTime());
            double longitude = FileUtility.findClosestPosition(longitudes, event.getOccurrenceTime());
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
            double latitude = FileUtility.findClosestPosition(latitudes, event.getOccurrenceTime());
            double longitude = FileUtility.findClosestPosition(longitudes, event.getOccurrenceTime());
            return new LatLongCoord(latitude, longitude);
        }
        return null;
    }



}
