package com.cus.client3.registrations.provider;

import org.springframework.security.oauth2.client.endpoint.NimbusAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * OAuth2AccessTokenResponseClient的组合类，可根据registrationId 选择相应的 OAuth2AccessTokenResponseClient的组合类
 */
public class CompositeOAuth2AccessTokenResponseClient implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {

    private Map<String,OAuth2AccessTokenResponseClient> clients;

    private static final String DefaultClientKey = "default_key";

    public CompositeOAuth2AccessTokenResponseClient() {
        this.clients =new HashMap<>();
        //spring-security-oauth2-client 默认的 OAuth2AccessTokenResponseClient的实现类是
        // NImbusAuthorizationCodeTokenResponseClient
        this.clients.put(DefaultClientKey,new NimbusAuthorizationCodeTokenResponseClient());

    }

    @Override
    public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {
        ClientRegistration clientRegistration = authorizationGrantRequest.getClientRegistration();
        OAuth2AccessTokenResponseClient client = clients.get(clientRegistration.getRegistrationId());
        if(client == null) {
            client = clients.get(DefaultClientKey);
        }
        return client.getTokenResponse(authorizationGrantRequest);
    }

    public Map<String,OAuth2AccessTokenResponseClient> getOAuth2AccessTokenResponseClients() {
        return clients;
    }
}
