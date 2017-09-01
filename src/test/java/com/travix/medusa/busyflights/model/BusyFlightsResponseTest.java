package com.travix.medusa.busyflights.model;

public class BusyFlightsResponseTest {
    
    public static BusyFlightsResponse createValidTestingResponse(String supplier, Double fare) {
        BusyFlightsResponse response = new BusyFlightsResponse();
        response.setAirline("Cheap Airline");
        response.setDestinationCode("AMS");
        response.setOriginCode("BAQ");
        response.setSupplier(supplier);
        response.setFare(fare);
        return response;
    }
}
