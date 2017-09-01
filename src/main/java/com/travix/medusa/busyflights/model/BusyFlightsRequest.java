package com.travix.medusa.busyflights.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

public class BusyFlightsRequest {

    @NotNull
    private String origin;
    @NotNull
    private String destination;
    @NotNull
    @DateTimeFormat(pattern="YYYY-MM-dd")
    private String departureDate;
    @NotNull
    @DateTimeFormat(pattern="YYYY-MM-dd")
    private String returnDate;
    @Max(4)
    @NotNull
    private int numberOfPassengers;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(final String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(final String returnDate) {
        this.returnDate = returnDate;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(final int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}
