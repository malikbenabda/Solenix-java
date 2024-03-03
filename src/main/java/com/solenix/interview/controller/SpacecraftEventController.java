package com.solenix.interview.controller;

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
    public String getEventById(@PathVariable String id) {
        if (id.isBlank()) {
            return "<p>Please enter an Id to lookup Coordinates of Boarding.</p>";
        }

        return  dataService.getEventWithPosition(id).toString();
    }

    @GetMapping("/")
    public String handleRoot() {
       return "<p> Root Page is empty ... <a href=\"./events\">Go back to events</a></p>";
    }
}
