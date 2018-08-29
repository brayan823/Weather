package com.exercise.dashboard.service;

import java.util.List;

import com.exercise.dashboard.domain.Temperature;

public interface TemperatureService {
	
	public List<Temperature> obtainTemperature(String city, String measure, int numberDays);

}
