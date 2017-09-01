package com.travix.medusa.busyflights.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

public interface HttpClient {
    
    HttpClient withUri(String uri);
    HttpClient withHttpMethod(HttpMethod method);
    HttpClient withRequestEntity(Object requestEntity);
    HttpClient withResponseType(ParameterizedTypeReference responseType);
    Object execute() throws JsonProcessingException;
}
