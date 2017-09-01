package com.travix.medusa.busyflights.provider.toughjet;

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

@Component("toughJetManager")
public class ToughJetManagerImpl implements ToughJetManager {

    @Autowired
    private HttpClient httpClient;
    
    // ToDo: Replace this URI with the actual api
    private final String URI = "http://www.toughjet.com/api";
    
    @Override
    public BusyFlightsResponse getSingleResult(BusyFlightsRequest originalRequest) throws JsonProcessingException {
        httpClient.withUri(URI)
            .withHttpMethod(HttpMethod.GET)
            .withRequestEntity(transformRequest(originalRequest))
            .withResponseType(new ParameterizedTypeReference<ToughJetResponse>() {});
        return transformResponse((ToughJetResponse) httpClient.execute());
    }

    @Override
    public List<BusyFlightsResponse> getList(BusyFlightsRequest originalRequest) throws JsonProcessingException {
        httpClient.withUri(URI)
            .withHttpMethod(HttpMethod.GET)
            .withRequestEntity(transformRequest(originalRequest))
            .withResponseType(new ParameterizedTypeReference<List<ToughJetResponse>>() {});
        return transformResponse((List<ToughJetResponse>) httpClient.execute());
    }

    private ToughJetRequest transformRequest(BusyFlightsRequest originalRequest) {
        ToughJetRequest toughJetRequest = new ToughJetRequest();
        toughJetRequest.setFrom(originalRequest.getOrigin());
        toughJetRequest.setTo(originalRequest.getDestination());
        toughJetRequest.setNumberOfAdults(originalRequest.getNumberOfPassengers());
        toughJetRequest.setOutboundDate(originalRequest.getDepartureDate());
        toughJetRequest.setInboundDate(originalRequest.getReturnDate());
        return toughJetRequest;
    }

    private BusyFlightsResponse transformResponse(ToughJetResponse toughJetResponse) {
        BusyFlightsResponse busyFlightResponse = new BusyFlightsResponse();
        busyFlightResponse.setSupplier("ToughJet");
        busyFlightResponse.setAirline(toughJetResponse.getCarrier());
        busyFlightResponse.setOriginCode(toughJetResponse.getDepartureAirportName());
        busyFlightResponse.setDestinationCode(toughJetResponse.getArrivalAirportName());
        busyFlightResponse.setDepartureTime(transformDateTime(toughJetResponse.getOutboundDateTime()));
        busyFlightResponse.setArrivalTime(transformDateTime(toughJetResponse.getInboundDateTime()));
        busyFlightResponse.setFare(calculatePrice(toughJetResponse));
        return busyFlightResponse;
    }

    private String transformDateTime(String toughJetDate) {
        TemporalAccessor date = DateTimeFormatter.ISO_INSTANT.parse(toughJetDate);
        return DateTimeFormatter.ISO_DATE_TIME.format(date);
    }

    private Double calculatePrice(ToughJetResponse toughJetResponse) {
        double price = toughJetResponse.getBasePrice();
        double discount = toughJetResponse.getDiscount() / 100;
        double tax = toughJetResponse.getTax();
        price = price - (price * discount);
        return price + tax;
    }

    private List<BusyFlightsResponse> transformResponse(List<ToughJetResponse> toughJetResponseList) {
        List<BusyFlightsResponse> responseList = new ArrayList();
        toughJetResponseList.forEach(toughJetResponse -> {
            responseList.add(transformResponse(toughJetResponse));
        });
        return responseList;
    }
}
