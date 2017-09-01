package com.travix.medusa.busyflights.manager;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpClientTest {

    @Autowired
    private HttpClient httpClient;

    @Test
    public void testProviderManager() throws JsonProcessingException {
        httpClient.withUri("http://services.groupkt.com/country/get/all")
                  .withHttpMethod(HttpMethod.GET)
                  .withRequestEntity(new ProviderRequestTest())
                  .withResponseType(new ParameterizedTypeReference<ProviderResponseTest>() { });
        Object responseEntity = httpClient.execute();
        Assert.assertTrue(responseEntity instanceof ProviderResponseTest);
        ProviderResponseTest response = (ProviderResponseTest) responseEntity;
        Assert.assertEquals(249, response.restResponse.result.size());
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ProviderRequestTest {

    }

    public static class ProviderResponseTest {

        @JsonProperty("RestResponse")
        public RestResponse restResponse;
    }

    public static class RestResponse {

        @JsonProperty("messages")
        public List<String> messages;
        @JsonProperty("result")
        public List<Result> result;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Result {

        @JsonProperty("name")
        public String name;
        @JsonProperty("alpha2_code")
        public String alpha2Code;
        @JsonProperty("alpha3_code")
        public String alpha3Code;
    }
}
