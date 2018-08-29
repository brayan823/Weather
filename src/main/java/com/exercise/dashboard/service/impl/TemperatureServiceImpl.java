package com.exercise.dashboard.service.impl;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exercise.dashboard.domain.Temperature;
import com.exercise.dashboard.domain.Weather;
import com.exercise.dashboard.exception.BadRequestException;
import com.exercise.dashboard.exception.ResourceNotFoundException;
import com.exercise.dashboard.service.TemperatureService;

@Service
@PropertySource("classpath:application.yml")
public class TemperatureServiceImpl implements TemperatureService{
	
	protected Logger logger = Logger.getLogger(TemperatureServiceImpl.class.getName());
	
	 @Autowired
	private Environment env;
	

	@Override
	public List<Temperature> obtainTemperature(String city, String measure, int numberDays) {
		RestTemplate restTemplate = new RestTemplate();
		Weather weather = new Weather();
		 Map<String, String> cities = new HashMap<>();
			cities.put("Caracas", "10.4880555, -66.8791667");
			cities.put("Santiago", "-33.4514023,-70.6601759");
			cities.put("Madrid", "40.41678, -3.70379");
			cities.put("Paris", "48.85661, 2.35222");
			cities.put("Berlin", "52.51917, 13.40609");
			cities.put("London", "51.51121, -0.11982");
			String coordinates ="";
			String units ="";
			String days = "";
			long timeStamp = Instant.now().getEpochSecond();
			if(cities.containsKey(city)) {
				coordinates = cities.get(city);
			} else {
				throw new ResourceNotFoundException("City not found");
			}
			 switch (measure.toLowerCase()) {
	            case "celsius":
	            	units = "&units=si";
	                break;
	            case "fahrenheit":
	            	units = "&units=us";
	                break;
	            default: 
	            	throw new BadRequestException("The Units must be Celsius or Fahrenheint");
			 }
			 
			 switch(numberDays) {
			 case 1:
				 days = ","+String.valueOf(timeStamp);
	                break;
	            case 7:
	            	days="";
	                break;
	            default: 
	            	throw new BadRequestException("The Days must be 7 or 1");
			 }
			 
			 weather = restTemplate.getForObject(
					 env.getProperty("url.base")+env.getProperty("api.key")+coordinates+days+env.getProperty("url.exclude")+units,Weather.class);
			return weather.getDaily().getData();
	}

}
