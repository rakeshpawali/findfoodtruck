package com.au.food.findfood.cache;


import com.au.food.findfood.beans.FoodTruckDetails;
import com.au.food.findfood.service.FindFoodService;
import com.google.common.base.Function;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class FoodTruckCacheManager extends AbstractCacheManager<String, List<FoodTruckDetails>> {

    @Autowired
    FindFoodService findFoodService;

    @Override
    protected Function<String,  List<FoodTruckDetails>> getValueLoader() {
        return this::buildCacheValue;
    }

    private  List<FoodTruckDetails> buildCacheValue(String key) {
        return findFoodService.getFoodTruckDetails();
    }
}
