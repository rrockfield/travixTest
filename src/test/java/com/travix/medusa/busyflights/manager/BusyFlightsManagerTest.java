package com.travix.medusa.busyflights.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.travix.medusa.busyflights.model.BusyFlightsRequest;
import com.travix.medusa.busyflights.model.BusyFlightsRequestTest;
import com.travix.medusa.busyflights.model.BusyFlightsResponse;
import com.travix.medusa.busyflights.model.BusyFlightsResponseTest;
import com.travix.medusa.busyflights.provider.crazyair.CrazyAirManager;
import com.travix.medusa.busyflights.provider.toughjet.ToughJetManager;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusyFlightsManagerTest {
    
    @InjectMocks
    private BusyFlightsManagerImpl busyFlightsManager;

    @Mock
    private CrazyAirManager crazyAirManager;

    @Mock
    private ToughJetManager toughJetManager;
    
    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void unitTestBusyFlightManager() throws JsonProcessingException {
        BusyFlightsRequest request = BusyFlightsRequestTest.createValidTestingRequest();
        BusyFlightsResponse crazyAirResponse = BusyFlightsResponseTest.createValidTestingResponse("CrazyAir", 1234.56);
        Mockito.when(crazyAirManager.getSingleResult(request)).thenReturn(crazyAirResponse);
        BusyFlightsResponse toughJetResponse = BusyFlightsResponseTest.createValidTestingResponse("ToughJet", 987.65);
        Mockito.when(toughJetManager.getSingleResult(request)).thenReturn(toughJetResponse);
        List<BusyFlightsResponse> response = busyFlightsManager.query(request);
        Assert.assertEquals(2, response.size());
        Assert.assertEquals(toughJetResponse, response.get(0));
        Assert.assertEquals(crazyAirResponse, response.get(1));
    }
}
