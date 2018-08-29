package com.exercise.dashboard.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.dashboard.domain.Temperature;
import com.exercise.dashboard.domain.Weather;
import com.exercise.dashboard.service.TemperatureService;

 	

@RestController
public class WeatherController {
	
	@Autowired
	private TemperatureService temperatureService;

	
	
	@RequestMapping(value = "/temperature/{city}/{measure}/{days}" , method = GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Temperature>> getTemperature(@PathVariable("city") String city, @PathVariable("measure") String measure, @PathVariable("days") int days) 
			throws InterruptedException{
		return new ResponseEntity<List<Temperature>>(temperatureService.obtainTemperature(city, measure, days), HttpStatus.OK);
		
	}
	

}
