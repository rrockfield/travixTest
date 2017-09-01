package com.travix.medusa.busyflights.provider.crazyair;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.travix.medusa.busyflights.model.BusyFlightsRequest;
import com.travix.medusa.busyflights.model.BusyFlightsResponse;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import com.travix.medusa.busyflights.manager.HttpClient;

@Component("crazyAirManager")
public class CrazyAirManagerImpl implements CrazyAirManager {

    @Autowired
    private HttpClient httpClient;
    
    // ToDo: Replace this URI with the actual api
    private final String URI = "http://www.crazyair.com/api";
    
    @Override
    public BusyFlightsResponse getSingleResult(BusyFlightsRequest originalRequest) throws JsonProcessingException {
        httpClient.withUri(URI)
            .withHttpMethod(HttpMethod.GET)
            .withRequestEntity(transformRequest(originalRequest))
            .withResponseType(new ParameterizedTypeReference<CrazyAirResponse>(){});
        return transformResponse((CrazyAirResponse) httpClient.execute());
    }

    @Override
    public List<BusyFlightsResponse> getList(BusyFlightsRequest originalRequest) throws JsonProcessingException {
        httpClient.withUri(URI)
            .withHttpMethod(HttpMethod.GET)
            .withRequestEntity(transformRequest(originalRequest))
            .withResponseType(new ParameterizedTypeReference<List<CrazyAirResponse>>() {});
        return transformResponse((List<CrazyAirResponse>) httpClient.execute());
    }

    private CrazyAirRequest transformRequest(BusyFlightsRequest originalRequest) {
        CrazyAirRequest crazyAirRequest = new CrazyAirRequest();
        crazyAirRequest.setOrigin(originalRequest.getOrigin());
        crazyAirRequest.setDestination(originalRequest.getDestination());
        crazyAirRequest.setPassengerCount(originalRequest.getNumberOfPassengers());
        crazyAirRequest.setDepartureDate(originalRequest.getDepartureDate());
        crazyAirRequest.setReturnDate(originalRequest.getReturnDate());
        return crazyAirRequest;
    }

    private BusyFlightsResponse transformResponse(CrazyAirResponse crazyAirResponse) {
        BusyFlightsResponse busyFlightResponse = new BusyFlightsResponse();
        busyFlightResponse.setSupplier("CrazyAir");
        busyFlightResponse.setAirline(crazyAirResponse.getAirline());
        busyFlightResponse.setOriginCode(crazyAirResponse.getDepartureAirportCode());
        busyFlightResponse.setDestinationCode(crazyAirResponse.getDestinationAirportCode());
        busyFlightResponse.setDepartureTime(transformDateTime(crazyAirResponse.getDepartureDate()));
        busyFlightResponse.setArrivalTime(transformDateTime(crazyAirResponse.getArrivalDate()));
        busyFlightResponse.setFare(crazyAirResponse.getPrice());
        return busyFlightResponse;
    }

    private String transformDateTime(String crazyAirDate) {
        TemporalAccessor date = DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(crazyAirDate);
        return DateTimeFormatter.ISO_DATE_TIME.format(date);
    }

    private List<BusyFlightsResponse> transformResponse(List<CrazyAirResponse> crazyAirResponseList) {
        List<BusyFlightsResponse> responseList = new ArrayList();
        crazyAirResponseList.forEach(crazyAirResponse -> {
            responseList.add(transformResponse(crazyAirResponse));
        });
        return responseList;
    }
}
