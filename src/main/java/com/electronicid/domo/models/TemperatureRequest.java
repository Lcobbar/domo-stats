package com.electronicid.domo.models;

import javax.validation.constraints.NotNull;
import java.util.List;

public class TemperatureRequest {

    @NotNull(message = "Readings can't be null")
    private List<Integer> readings;

    public TemperatureRequest() {
        // Sonar
    }

    public List<Integer> getReadings() {
        return readings;
    }

    public void setReadings(List<Integer> readings) {
        this.readings = readings;
    }

}
