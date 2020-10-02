# CSE Code Challenge
https://github.com/timfpark/take-home-engineering-challenge/blob/main/README.md

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## Run the application
```
> git clone https://github.com/rakeshpawali/findfoodtruck.git
> cd findfoodtruck
> ./gradlew bootRun
```
## Endpoints Exposed
|                  URL                   | Method |          Remarks       |
|----------------------------------------|--------|------------------------|
|`http://localhost:8080/findfoodtruck?longitude=<double>>&latitude=<double>&noOfResults=<int>`           | GET    | Find Food Trucks based on query params               |

## Sample Request and Response

```
http://localhost:8080/findfoodtruck?longitude=-122.397726709152&latitude=37.7875398934675&noOfResults=2`
```
```json

[
    {
        "distance": 0.023491975912466205,
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
    },
    {
        "distance": 0.03920102536035149,
        "foodTruckDetails": {
            "locationid": "1339633",
            "Applicant": "Plaza Garibaldy",
            "Latitude": 37.7879549596858,
            "Longitude": -122.397236543731,
            "FacilityType": "Truck",
            "LocationDescription": "HOWARD ST: 01ST ST to MALDEN ALY (500 - 589)",
            "Address": "540 HOWARD ST",
            "FoodItems": "Tacos: burritos: quesadillas",
            "Schedule": "http://bsm.sfdpw.org/PermitsTracker/reports/report.aspx?title=schedule&report=rptSchedule&params=permit=19MFF-00100&ExportPDF=1&Filename=19MFF-00100_schedule.pdf",
            "Location": "(37.7879549596858, -122.397236543731)"
        }
    }
]
```
