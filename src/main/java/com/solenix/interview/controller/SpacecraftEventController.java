package com.solenix.interview.controller;

import com.solenix.interview.model.LatLongCoord;
import com.solenix.interview.model.SpacecraftEvent;
import com.solenix.interview.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class SpacecraftEventController {
    @Autowired
    private final DataService dataService;



    public SpacecraftEventController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/events")
    public List<SpacecraftEvent> getAllEvents() {
        return dataService.getAllEventsWithPositions();
    }

    @GetMapping("/events/{id}")
    public LatLongCoord getEventById(@PathVariable String id) {
        return dataService.getEventWithPosition(id);
    }
}
