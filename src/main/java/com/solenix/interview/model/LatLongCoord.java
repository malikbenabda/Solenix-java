package com.solenix.interview.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class LatLongCoord {

    private double latitude;
    private double longitude;
    @JsonCreator
    public LatLongCoord(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }



    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
