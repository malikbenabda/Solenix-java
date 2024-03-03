package com.solenix.interview.service;

import com.solenix.interview.model.LatLongCoord;
import com.solenix.interview.model.SpacecraftEvent;

import java.util.List;

public interface DataService {
    List<SpacecraftEvent> getAllEventsWithPositions();
    LatLongCoord getEventWithPosition(String id);
}