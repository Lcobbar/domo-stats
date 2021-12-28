package com.electronicid.domo.services;

import com.electronicid.domo.exceptions.TemperatureComputeException;

import java.util.List;

public interface TemperatureComputeService {

    Integer compute(List<Integer> readings) throws TemperatureComputeException;

}
