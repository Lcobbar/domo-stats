package com.electronicid.domo.controllers;

import com.electronicid.domo.exceptions.TemperatureComputeException;
import com.electronicid.domo.models.TemperatureRequest;
import com.electronicid.domo.services.TemperatureComputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v2/stats")
public class TemperatureController {

    private final TemperatureComputeService temperatureComputeService;

    @Autowired
    public TemperatureController(TemperatureComputeService temperatureComputeService) {
        this.temperatureComputeService = temperatureComputeService;
    }

    @PostMapping(value="/compute")
    public Integer compute(@Valid @RequestBody TemperatureRequest temperatureRequest) throws TemperatureComputeException{
        return temperatureComputeService.compute(temperatureRequest.getReadings());
    }

}
