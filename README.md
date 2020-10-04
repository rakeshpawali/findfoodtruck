# CSE Code Challenge

## Problem Statement
[Coding Challenge](https://github.com/timfpark/take-home-engineering-challenge/blob/main/README.md)

## Solution Summary 

The solution uses JAVA programming language. Below are the salient features.


- A REST API to search food trucks based on longitude and latitude. 
- Ability to get specified no of results
- Utilises San Francisco's food truck open dataset [csv](https://data.sfgov.org/api/views/rqzj-sfat/rows.csv) endpoint
- SFO food truck dataset cached locally and refreshed on a daily (configurable) bases
- Uses Google Android Location API to calculate the distance between given coordinates.



## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## Run the application
```
> git clone https://github.com/rakeshpawali/findfoodtruck.git
> cd findfoodtruck
> ./gradlew bootRun
```
## Endpoints 
|                  URL                   | Method |          Remarks       |
|----------------------------------------|--------|------------------------|
|`http://localhost:8080/findfoodtruck?longitude=<double>>&latitude=<double>&noOfResults=<int>`           | GET    | Find Food Trucks based on query params               |

## Sample Request and Response

```
http://localhost:8080/findfoodtruck?longitude=-122.397726709152&latitude=37.7875398934675&noOfResults=2
```
```json

[
    {
        "distance": 0,
        "foodTruckDetails": {
            "locationid": "1447794",
            "Applicant": "Street Meet",
            "Latitude": 37.7875398934675,
            "Longitude": -122.397726709152,
            "FacilityType": "Truck",
            "LocationDescription": "HOWARD ST: 01ST ST to MALDEN ALY (500 - 589)",
            "Address": "564 HOWARD ST",
            "FoodItems": "Tortas: Burritos: Tacos: Churros: Nachos: Asada Fries",
            "Schedule": "http://bsm.sfdpw.org/PermitsTracker/reports/report.aspx?title=schedule&report=rptSchedule&params=permit=20MFF-00007&ExportPDF=1&Filename=20MFF-00007_schedule.pdf",
            "Location": "(37.7875398934675, -122.397726709152)"
        }
    },
    {
        "distance": 0.023146387133832682,
        "foodTruckDetails": {
            "locationid": "1367290",
            "Applicant": "Star Taco",
            "Latitude": 37.7873042488646,
            "Longitude": -122.398037251912,
            "FacilityType": "",
            "LocationDescription": "HOWARD ST: MALDEN ALY to 02ND ST (574 - 599)",
            "Address": "580 HOWARD ST",
            "FoodItems": "Mexican Food",
            "Schedule": "http://bsm.sfdpw.org/PermitsTracker/reports/report.aspx?title=schedule&report=rptSchedule&params=permit=19MFF-00128&ExportPDF=1&Filename=19MFF-00128_schedule.pdf",
            "Location": "(37.7873042488646, -122.398037251912)"
        }
    }
]
```
