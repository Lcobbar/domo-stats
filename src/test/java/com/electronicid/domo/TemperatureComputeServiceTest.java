package com.electronicid.domo;

import com.electronicid.domo.exceptions.TemperatureComputeException;
import com.electronicid.domo.services.TemperatureComputeService;
import com.electronicid.domo.services.impl.TemperatureComputeServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class TemperatureComputeServiceTest {

    private TemperatureComputeService temperatureComputeService;

    @Before
    public void setUp(){
        temperatureComputeService = new TemperatureComputeServiceImpl();
    }

    @Test
    public void compute_0_empty() throws TemperatureComputeException {
        List<Integer> readings = new ArrayList<>();
        Assert.assertEquals(0, (int) temperatureComputeService.compute(readings));
    }

    @Test
    public void compute_0_decrescendo() throws TemperatureComputeException {
        List<Integer> readings = new ArrayList<>(List.of(3,2,1));
        Assert.assertEquals(0, (int) temperatureComputeService.compute(readings));
    }

    @Test
    public void compute_0_equals() throws TemperatureComputeException {
        List<Integer> readings = new ArrayList<>(List.of(1, 1, 1));
        Assert.assertEquals(0, (int) temperatureComputeService.compute(readings));
    }

    @Test
    public void compute_2() throws TemperatureComputeException {
        List<Integer> readings = new ArrayList<>(List.of(1, 2, 3));
        Assert.assertEquals(2, (int) temperatureComputeService.compute(readings));
    }

    @Test
    public void compute_3() throws TemperatureComputeException {
        List<Integer> readings = new ArrayList<>(List.of(3,2,1,4));
        Assert.assertEquals(3, (int) temperatureComputeService.compute(readings));
    }

    @Test
    public void compute_8() throws TemperatureComputeException {
        List<Integer> readings = new ArrayList<>(List.of(10, 15, 18, 1, 8));
        Assert.assertEquals(8, (int) temperatureComputeService.compute(readings));
    }

    @Test
    public void compute_35() throws TemperatureComputeException {
        List<Integer> readings = new ArrayList<>(List.of(10, -15, 20, 1, 8));
        Assert.assertEquals(35, (int) temperatureComputeService.compute(readings));
    }

    @Test(expected = TemperatureComputeException.class)
    public void compute_throwException() throws TemperatureComputeException {
        List<Integer> readings = new ArrayList<>();
        readings.add(1);
        readings.add(null);
        temperatureComputeService.compute(readings);
    }
    
}
