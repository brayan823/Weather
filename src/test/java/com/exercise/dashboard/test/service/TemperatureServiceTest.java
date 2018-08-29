package com.exercise.dashboard.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.exercise.dashboard.domain.Temperature;
import com.exercise.dashboard.domain.Weather;
import com.exercise.dashboard.service.impl.TemperatureServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemperatureServiceTest {
	
	@InjectMocks
	private TemperatureServiceImpl temperatureService;
	
	@Mock
	private Environment env;

	
	@SuppressWarnings("unchecked")
	@Test
    public void obtainTemperature() {

		
		RestTemplate template = Mockito.mock(RestTemplate.class);
		when(env.getProperty("url.base")).thenReturn("https://api.forecast.io/forecast/");
		when(env.getProperty("api.key")).thenReturn("f494fe4658955ce33e8331352513e2d2/");
		when(env.getProperty("url.exclude")).thenReturn("?exclude=minutely,hourly,alerts,flags,currently");
		when(template.getForObject(any(String.class), any(Class.class))).thenReturn(new ResponseEntity(new Weather(), HttpStatus.OK));
        // I search for goku but system will use mocked response containing only ken, so I can check that mock is used.
        List<Temperature> weather = temperatureService.obtainTemperature("Caracas", "Celsius", 1);
        assertThat(weather).isNotNull();

    }
	
}
