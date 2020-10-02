package com.au.food.findfood.service;

import com.au.food.findfood.cache.FoodTruckCacheManager;
import com.au.food.findfood.beans.FoodTruckDetails;
import com.au.food.findfood.util.Location;
import com.au.food.findfood.beans.SearchResult;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import static java.util.stream.Collectors.toMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindFoodServiceImpl implements FindFoodService{

    @Autowired
    private FoodTruckCacheManager foodTruckCacheManager;

    @Value("${sfo.dataset.url}")
    private String sfoFoodtruckUrl;

    @Override
    public List<FoodTruckDetails> getFoodTruckDetails()  {
        System.out.println("Calling San Francisco's food truck open dataset service");
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
        ObjectMapper mapper = new CsvMapper();
        List<FoodTruckDetails> foodTruckDetailsList = new ArrayList<>();
        try {

            MappingIterator<FoodTruckDetails> it = mapper.readerFor(FoodTruckDetails.class)
                    .with(bootstrapSchema).readValues(new URL(sfoFoodtruckUrl));
            foodTruckDetailsList =  it.readAll();
        } catch (MalformedInputException  me){

        } catch (IOException ie){

        }
        return  foodTruckDetailsList;
    }

    @Override
    public List<SearchResult> findNearestFoodTruck(double latitude, double longitude, int noOfTrucks) throws ExecutionException {

            Location userLocation = new Location(latitude, longitude);

            HashMap<FoodTruckDetails, Double> distantceMap = new HashMap<>();
            for (FoodTruckDetails foodTruckDetails : foodTruckCacheManager.getValue("foodTruckDetails")){
                double distance = userLocation.distanceTo(new Location(foodTruckDetails.getLatitude(),foodTruckDetails.getLongitude()));
                distantceMap.put(foodTruckDetails, distance);
            }

            // let's sort this map by values first
        HashMap<FoodTruckDetails, Double> sortedMap = distantceMap
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                            LinkedHashMap::new));

        return getDetails(noOfTrucks, sortedMap.entrySet());


        }

    /**
     * returns required number of search results.
     * @param noOfTrucks
     * @param entries
     * @return {@link List<SearchResult> }
     */
    private List<SearchResult> getDetails(int noOfTrucks, Set<Map.Entry<FoodTruckDetails, Double>> entries) {
        List<SearchResult> resultList = new ArrayList<>();
        int counter = 0;
        for (Map.Entry<FoodTruckDetails, Double> entry : entries) {
            resultList.add(new SearchResult(entry.getValue(), entry.getKey()));
            counter++;
            if(counter == noOfTrucks){
                break;
            }
        }
        return resultList;
    }
}
