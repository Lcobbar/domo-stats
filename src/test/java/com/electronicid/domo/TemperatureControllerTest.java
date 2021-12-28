package com.electronicid.domo;

import com.electronicid.domo.controllers.TemperatureController;
import com.electronicid.domo.exceptions.TemperatureComputeException;
import com.electronicid.domo.services.TemperatureComputeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.electronicid.domo.models.TemperatureRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureControllerTest {

    @Mock
    private TemperatureComputeService temperatureComputeService;

    @InjectMocks
    private TemperatureController temperatureController;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        JacksonTester.initFields(this, new ObjectMapper());

        mockMvc = MockMvcBuilders.standaloneSetup(temperatureController).
                build();
    }

    @Test
    public void computeStats() throws Exception{
        TemperatureRequest request = new TemperatureRequest();
        List<Integer> readings = List.of(1);
        request.setReadings(readings);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);

        //given
        given(temperatureComputeService.compute(request.getReadings()))
                .willReturn(0);
        //when
        MockHttpServletResponse response = mockMvc.perform(
                post("/v2/stats/compute")
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(json))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("0");
    }

    @Test
    public void computeStats_throwException() throws Exception{
        TemperatureRequest request = new TemperatureRequest();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(null);
        request.setReadings(list);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);

        //given
        given(temperatureComputeService.compute(request.getReadings()))
               .willThrow(new TemperatureComputeException("A reading is null or empty"));

        //when
        MockHttpServletResponse response = mockMvc.perform(
                post("/v2/stats/compute")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().is5xxServerError())
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
