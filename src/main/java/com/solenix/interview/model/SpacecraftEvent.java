package com.solenix.interview.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class SpacecraftEvent {
    private String id;
    @JsonProperty("event_name")
    private String eventName;

    private String severity;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("occurrence_time")
    private Date occurrenceTime;

    public SpacecraftEvent() {
    }


    private double latitudePosition;
    private double longitudePosition;

    public SpacecraftEvent(Date occurrenceTime, String eventName, String id, String severity, double latitudePosition, double longitudePosition) {
        this.occurrenceTime = occurrenceTime;
        this.eventName = eventName;
        this.id = id;
        this.severity = severity;
        this.longitudePosition = longitudePosition;
        this.latitudePosition = latitudePosition;
    }

    public SpacecraftEvent(double latitudePosition, double longitudePosition) {
        this.latitudePosition = latitudePosition;
        this.longitudePosition = longitudePosition;
    }

    @JsonCreator
    public SpacecraftEvent(@JsonProperty("occurrence_time") Date occurrenceTime,
                           @JsonProperty("event_name") String eventName,
                           @JsonProperty("id") String id,
                           @JsonProperty("severity") String severity) {
        this.occurrenceTime = occurrenceTime;
        this.eventName = eventName;
        this.id = id;
        this.severity = severity;
    }

    public Date getOccurrenceTime() {
        return occurrenceTime;
    }

    public void setOccurrenceTime(Date occurrenceTime) {
        this.occurrenceTime = occurrenceTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public double getLongitudePosition() {
        return longitudePosition;
    }

    public void setLongitudePosition(double longitudePosition) {
        this.longitudePosition = longitudePosition;
    }

    public double getLatitudePosition() {
        return latitudePosition;
    }

    public void setLatitudePosition(double latitudePosition) {
        this.latitudePosition = latitudePosition;
    }

    @Override
    public String toString() {
        return "SpacecraftEvent{" +
                "occurrenceTime=" + occurrenceTime +
                ", eventName='" + eventName + '\'' +
                ", id='" + id + '\'' +
                ", severity='" + severity + '\'' +
                ", {latitude:" + latitudePosition +
                ", longitudePosition:" + longitudePosition + '}'+
                "} \n";
    }
}