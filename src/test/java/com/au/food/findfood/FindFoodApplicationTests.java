package com.au.food.findfood;

import com.au.food.findfood.controller.FoodTruckController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FindFoodApplicationTests {

	@Autowired
	FoodTruckController foodTruckController;
	@Test
	void contextLoads() {

		assertThat(foodTruckController).isNotNull();
	}

}
