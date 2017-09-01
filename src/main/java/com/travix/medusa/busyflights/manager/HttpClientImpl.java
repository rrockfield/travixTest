package com.travix.medusa.busyflights.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("httpClient")
public class HttpClientImpl implements HttpClient {

    //@Autowired
    private final RestTemplate template = new RestTemplate();
    private String uri;
    private HttpMethod method;
    private Object requestEntity;
    private ParameterizedTypeReference responseType;

    @Override
    public HttpClient withUri(String uri) {
        this.uri = uri;
        return this;
    }

    @Override
    public HttpClient withHttpMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    @Override
    public HttpClient withRequestEntity(Object requestEntity) {
        this.requestEntity = requestEntity;
        return this;
    }

    @Override
    public HttpClient withResponseType(ParameterizedTypeReference responseType) {
        this.responseType = responseType;
        return this;
    }

    @Override
    public Object execute() throws JsonProcessingException {
        HttpEntity<String> entity = toJSONHttpEntity(requestEntity);
        return template.exchange(uri, method, entity, responseType).getBody();
    }

    private HttpEntity<String> toJSONHttpEntity(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);
        HttpHeaders headers = createHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity(json, headers);
        return entity;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> list = new ArrayList();
        list.add(MediaType.APPLICATION_JSON);
        headers.setAccept(list);
        return headers;
    }
}
