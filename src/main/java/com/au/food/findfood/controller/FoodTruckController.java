package com.au.food.findfood.controller;

import com.au.food.findfood.service.FindFoodService;
import com.au.food.findfood.beans.SearchResult;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FoodTruckController {

    @Autowired
     FindFoodService findFoodService;

    /**
     * Exposes API to search for food trucks
     * @param latitude
     * @param longitude
     * @param noOfResults
     * @return  {@link List<SearchResult>}
     * @throws ExecutionException
     */
    @RequestMapping(method = RequestMethod.GET, value = "/findfoodtruck", produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public List<SearchResult> findFoodTrucks(@RequestParam(value = "latitude", required = true) double latitude,
                                             @RequestParam(value = "longitude", required = true) double longitude,
                                             @RequestParam(value = "noOfResults", required = true) int noOfResults) throws ExecutionException {
        return findFoodService.findNearestFoodTruck(latitude, longitude, noOfResults);
    }
}
