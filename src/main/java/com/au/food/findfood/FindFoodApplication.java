package com.au.food.findfood;

import com.au.food.findfood.cache.FoodTruckCacheManager;
import java.util.concurrent.ExecutionException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FindFoodApplication {
 @Autowired
	FoodTruckCacheManager foodTruckCacheManager;
	public static void main(String[] args) {
		SpringApplication.run(FindFoodApplication.class, args);
	}

	/**
	 * This method loads the San Francisco's food truck open dataset during application startup.
	 * For subsequent calls the cache returns the data sets, thus avoiding external call everytime.
	 * The cache loading can be configured in application properties.
	 */
	@PostConstruct
	public void populateFoodTruckCache(){
		try {
			foodTruckCacheManager.getValue("foodTruckDetails");
		}catch (ExecutionException e){

		}
	}
}
