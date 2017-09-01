package com.travix.medusa.busyflights.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.travix.medusa.busyflights.manager.BusyFlightsManager;
import com.travix.medusa.busyflights.model.BusyFlightsRequest;
import com.travix.medusa.busyflights.model.BusyFlightsResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api")
public class BusyFlightsController {

    @Autowired
    private BusyFlightsManager manager;

    @RequestMapping(value = "/",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BusyFlightsResponse> query(@RequestBody BusyFlightsRequest request) throws JsonProcessingException {
        return manager.query(request);
    }
}
