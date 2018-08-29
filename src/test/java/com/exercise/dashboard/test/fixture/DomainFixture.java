package com.exercise.dashboard.test.fixture;

import java.util.ArrayList;
import java.util.List;

import com.exercise.dashboard.domain.Daily;
import com.exercise.dashboard.domain.Temperature;
import com.exercise.dashboard.domain.Weather;

public class DomainFixture {

	public static List<Temperature> buildWeather() {

		List<Temperature> list = new ArrayList();
		Temperature temperature = new Temperature();
		temperature.setTemperatureHigh("39");
		temperature.setTemperatureLow("39");
		list.add(temperature);
		return list;
	}
}
