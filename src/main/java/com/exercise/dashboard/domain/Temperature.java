package com.exercise.dashboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Temperature {
	
	@JsonProperty
	private String temperatureHigh;
	@JsonProperty
	private String temperatureLow;
	
	public String getTemperatureHigh() {
		return temperatureHigh;
	}
	public void setTemperatureHigh(String temperatureHigh) {
		this.temperatureHigh = temperatureHigh;
	}
	public String getTemperatureLow() {
		return temperatureLow;
	}
	public void setTemperatureLow(String temperatureLow) {
		this.temperatureLow = temperatureLow;
	}
	
	

}
