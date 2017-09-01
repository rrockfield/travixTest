package com.travix.medusa.busyflights;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.travix.medusa.busyflights.model.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import com.travix.medusa.busyflights.manager.HttpClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusyFlightsApplicationTests {

    @LocalServerPort
    int PORT;

    @Autowired
    private HttpClient httpClient;

    @Test
    // Eliminate the Ignore annotation when the provider URIs have been validated.
    // Then execute the integration test for connecting to all providers.
    @Ignore
	public void integrationTest() throws JsonProcessingException {
        BusyFlightsRequest request = BusyFlightsRequestTest.createValidTestingRequest();
        httpClient.withUri("http://localhost:" + PORT + "/api/")
                  .withHttpMethod(HttpMethod.POST)
                  .withRequestEntity(request)
                  .withResponseType(new ParameterizedTypeReference<String>() { });
        String response = (String) httpClient.execute();
        System.out.println(response);
    }
}
