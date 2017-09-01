package com.travix.medusa.busyflights.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.travix.medusa.busyflights.provider.crazyair.CrazyAirManager;
import com.travix.medusa.busyflights.provider.toughjet.ToughJetManager;
import com.travix.medusa.busyflights.model.BusyFlightsRequest;
import com.travix.medusa.busyflights.model.BusyFlightsResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("queryManager")
public class BusyFlightsManagerImpl implements BusyFlightsManager {
    
    @Autowired
    private CrazyAirManager crazyAirManager;

    @Autowired
    private ToughJetManager toughJetManager;
    
    // ToDo: Add more managers here
    
    @Override
    public List<BusyFlightsResponse> query(BusyFlightsRequest request) throws JsonProcessingException {
        List<BusyFlightsResponse> flightList = new ArrayList();
        //orderedMerge(flightList, crazyAirManager.getList(request));
        orderedAggregation(flightList, crazyAirManager.getSingleResult(request));
        //orderedMerge(flightList, toughJetManager.getList(request));
        orderedAggregation(flightList, toughJetManager.getSingleResult(request));
        // ToDo: Add more responses from new managers here
        return flightList;
    }

    /**
     * This method was meant to be used when the provider returns a list of flights instead of just one.
     */
    private void orderedMerge(List<BusyFlightsResponse> currentFlightList, List<BusyFlightsResponse> newFlights) {
        newFlights.forEach(flight -> {
            orderedAggregation(currentFlightList, flight);
        });
    }

    private int orderedAggregation(List<BusyFlightsResponse> currentFlightList, BusyFlightsResponse newFlight) {
        for (int i = 0; i < currentFlightList.size(); i++) {
            BusyFlightsResponse currentFlight = currentFlightList.get(i);
            if (newFlight.getFare() < currentFlight.getFare()) {
                currentFlightList.add(i, newFlight);
                return i;
            }
        }
        currentFlightList.add(newFlight);
        return currentFlightList.size() - 1;
    }
}
