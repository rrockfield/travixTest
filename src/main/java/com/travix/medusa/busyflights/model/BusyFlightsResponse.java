package com.travix.medusa.busyflights.model;

import javax.validation.constraints.NotNull;

public class BusyFlightsResponse {
    
    @NotNull
    private String airline;
    @NotNull
    private String supplier;
    @NotNull
    private Double fare;
    @NotNull
    private String originCode;
    @NotNull
    private String destinationCode;
    @NotNull
    private String departureTime;
    @NotNull
    private String arrivalTime;

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void add(BusyFlightsResponse response) {
        
    }
}
