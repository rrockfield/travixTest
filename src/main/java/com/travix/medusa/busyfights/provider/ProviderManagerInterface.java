package com.travix.medusa.busyfights.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.travix.medusa.busyflights.model.BusyFlightsRequest;
import com.travix.medusa.busyflights.model.BusyFlightsResponse;
import java.util.List;

/**
 * All provider interfaces should extend this class in order to avoid duplicated code
 * @author lroca
 */
public interface ProviderManagerInterface {
    
    BusyFlightsResponse getSingleResult(BusyFlightsRequest originalRequest) throws JsonProcessingException;

    List<BusyFlightsResponse> getList(BusyFlightsRequest originalRequest) throws JsonProcessingException;
}
