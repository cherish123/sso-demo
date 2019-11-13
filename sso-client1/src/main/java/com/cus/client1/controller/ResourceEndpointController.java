package com.cus.client1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ResourceEndpointController {

    private static final String URL_TO_CLIENT1 = "http://127.0.0.1:9999/server/phone";

    private RestTemplate restTemplate;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    private RestTemplate getRestTemplate() {
        if(restTemplate == null ) {
            restTemplate = new RestTemplate();
        }
        return restTemplate;
    }

    @GetMapping("/phone")
    public String userinfo(OAuth2AuthenticationToken authentication){

        OAuth2AuthorizedClient oAuth2AuthorizedClient = authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),authentication.getName()
        );

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization","bearer "+oAuth2AuthorizedClient.getAccessToken().getTokenValue());

        HttpEntity<String> requestEntity = new HttpEntity<String>(null,httpHeaders);
        ResponseEntity<String> response = getRestTemplate().exchange(URL_TO_CLIENT1, HttpMethod.GET,requestEntity,String.class);
        return response.getBody();
    }

}