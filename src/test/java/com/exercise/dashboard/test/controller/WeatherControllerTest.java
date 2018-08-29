package com.exercise.dashboard.test.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.exercise.dashboard.controller.WeatherController;
import com.exercise.dashboard.service.TemperatureService;
import com.exercise.dashboard.test.fixture.DomainFixture;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
    private WeatherController weatherController;
	
	 @Mock
	 TemperatureService temperatureService;
	 
	 @Before
	    public void init(){
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders
	                .standaloneSetup(weatherController)
	                .build();
	    }
	    

	 
	 @Test
	    public void getTemperature() throws Exception {

	        when(temperatureService.obtainTemperature(any(String.class), any(String.class), any(Integer.class))).thenReturn(DomainFixture.buildWeather());
	        
	        
	        mockMvc.perform(get("/temperature/Caracas/Celsius/1").contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", hasSize(1)))
	            .andExpect(jsonPath("$.[0].temperatureHigh").exists())
	        	.andExpect(jsonPath("$.[0].temperatureLow").value("39"));
	    }

}
