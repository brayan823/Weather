package com.exercise.dashboard.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Daily {
	
	@JsonProperty
	private List<Temperature> data;

	public List<Temperature> getData() {
		return data;
	}

	public void setData(List<Temperature> data) {
		this.data = data;
	}
	
	
	
	

}
