package com.au.food.findfood.service;

import com.au.food.findfood.beans.FoodTruckDetails;
import com.au.food.findfood.beans.SearchResult;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface FindFoodService {

    /**
     * Calls the San Francisco's food truck open dataset endpoint
     * @return {@link List<FoodTruckDetails>}
     */
    List<FoodTruckDetails> getFoodTruckDetails();

    /**
     * Finds and provides nearest food trucks for given location
     * @param latitude
     * @param longitude
     * @param noOfTrucks
     * @return {@link List<SearchResult>}
     * @throws ExecutionException
     */
    List<SearchResult> findNearestFoodTruck(double latitude, double longitude, int noOfTrucks) throws ExecutionException;
}
