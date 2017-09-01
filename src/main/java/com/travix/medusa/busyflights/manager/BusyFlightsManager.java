package com.travix.medusa.busyflights.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.travix.medusa.busyflights.model.BusyFlightsRequest;
import com.travix.medusa.busyflights.model.BusyFlightsResponse;
import java.util.List;

public interface BusyFlightsManager {

    List<BusyFlightsResponse> query(BusyFlightsRequest request) throws JsonProcessingException;
}
