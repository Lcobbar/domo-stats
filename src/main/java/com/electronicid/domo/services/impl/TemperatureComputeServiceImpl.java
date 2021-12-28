package com.electronicid.domo.services.impl;

import com.electronicid.domo.exceptions.TemperatureComputeException;
import com.electronicid.domo.services.TemperatureComputeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureComputeServiceImpl implements TemperatureComputeService {

    @Override
    public Integer compute(List<Integer> readings) throws TemperatureComputeException {
        throwIfAnyElementNull(readings);
        int increment = 0;
        for (int i = 0; i < readings.size(); i++) {
            for (int j = i + 1; j < readings.size(); j++) {
                if (readings.get(j) > readings.get(i)) {
                    int aux = readings.get(j) - readings.get(i);
                    if (aux > increment) {
                        increment = aux;
                    }
                }
            }
        }
        return increment;
    }

    private void throwIfAnyElementNull(List<Integer> readings) throws TemperatureComputeException {
        if(readings.contains(null)) {
            throw new TemperatureComputeException("A reading is null or empty");
        }
    }

}
