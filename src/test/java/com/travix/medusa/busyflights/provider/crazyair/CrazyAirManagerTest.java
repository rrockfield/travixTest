package com.travix.medusa.busyflights.provider.crazyair;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.travix.medusa.busyflights.manager.HttpClient;
import com.travix.medusa.busyflights.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrazyAirManagerTest {

    @InjectMocks
    private CrazyAirManagerImpl crazyAirManager;
    
    @Mock
    private HttpClient httpClient;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void unitTestCrazyAirManager() throws JsonProcessingException {
        BusyFlightsRequest request = BusyFlightsRequestTest.createValidTestingRequest();
        CrazyAirResponse crazyAirResponse = createValidTestingResponse();
        Mockito.when(httpClient.withUri("http://www.crazyair.com/api")).thenReturn(httpClient);
        Mockito.when(httpClient.withHttpMethod(HttpMethod.GET)).thenReturn(httpClient);
        Mockito.when(httpClient.withRequestEntity(Matchers.anyObject())).thenReturn(httpClient);
        Mockito.when(httpClient.withResponseType(Matchers.anyObject())).thenReturn(httpClient);
        Mockito.when(httpClient.execute()).thenReturn(crazyAirResponse);
        BusyFlightsResponse response = crazyAirManager.getSingleResult(request);
        Assert.assertEquals("CrazyAir", response.getSupplier());
        Assert.assertEquals("Cheap Airline", response.getAirline());
        Assert.assertEquals("BAQ", response.getOriginCode());
        Assert.assertEquals("AMS", response.getDestinationCode());
        Assert.assertEquals(new Double(123.56), response.getFare());
        Assert.assertEquals("2018-04-30T10:15:30", response.getDepartureTime());
        Assert.assertEquals("2018-05-01T10:15:30", response.getArrivalTime());
    }

    private CrazyAirResponse createValidTestingResponse() {
        CrazyAirResponse response = new CrazyAirResponse();
        response.setAirline("Cheap Airline");
        response.setDepartureAirportCode("BAQ");
        response.setDestinationAirportCode("AMS");
        response.setCabinclass("B");
        response.setPrice(123.56);
        response.setDepartureDate("2018-04-30T10:15:30");
        response.setArrivalDate("2018-05-01T10:15:30");
        return response;
    }
}
