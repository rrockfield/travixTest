package com.travix.medusa.busyflights.model;

public class BusyFlightsRequestTest {
    
    public static BusyFlightsRequest createValidTestingRequest() {
        BusyFlightsRequest request = new BusyFlightsRequest();
        request.setOrigin("BAQ");
        request.setDestination("AMS");
        request.setDepartureDate("2018-05-01");
        request.setReturnDate("2018-12-15");
        request.setNumberOfPassengers(3);
        return request;
    }
}
