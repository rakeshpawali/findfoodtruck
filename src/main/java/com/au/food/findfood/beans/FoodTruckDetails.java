package com.au.food.findfood.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Bean to parse the csv from San Francisco's food truck open dataset.
 * Additional fields can be added as required
 */
public class FoodTruckDetails {

    @JsonProperty("locationid")
    private String locationId;
    @JsonProperty("Applicant")
    private String applicant;
    @JsonProperty("Latitude")
    private double latitude;
    @JsonProperty("Longitude")
    private double longitude;
    @JsonProperty("FacilityType")
    private String facilityType;
    @JsonProperty("LocationDescription")
    private String locationDescription;
    @JsonProperty("Address")
    private String address;
    @JsonProperty("FoodItems")
    private String foodItems;
    @JsonProperty("Schedule")
    private String schedule;
    @JsonProperty("Location")
    private String location;

}
