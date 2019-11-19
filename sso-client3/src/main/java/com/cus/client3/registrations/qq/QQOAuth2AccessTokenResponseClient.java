package com.cus.client3.registrations.qq;

import com.cus.client3.untils.TextHtmlHttpMessageConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.client.endpoint.AbstractOAuth2AuthorizationGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationExchange;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class QQOAuth2AccessTokenResponseClient<O extends AbstractOAuth2AuthorizationGrantRequest> implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {

    private RestTemplate restTemplate;

    private RestTemplate getRestTemplate() {
        if(restTemplate == null) {
            restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new TextHtmlHttpMessageConverter());
        }
        return restTemplate;
    }

    @Override
    public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {

        ClientRegistration clientRegistration = authorizationGrantRequest.getClientRegistration();
        OAuth2AuthorizationExchange authorizationExchange = authorizationGrantRequest.getAuthorizationExchange();

        //根据API文档获取请求access_token参数
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.set("client_id",clientRegistration.getClientId());
        params.set("client_secret",clientRegistration.getClientSecret());
        params.set("code",authorizationExchange.getAuthorizationResponse().getCode());
        params.set("redirect_uri",authorizationExchange.getAuthorizationResponse().getRedirectUri());
        params.set("grant_type","authorization_code");

        String tmpTokenResponse = getRestTemplate().postForObject(clientRegistration.getProviderDetails().getTokenUri(), params, String.class);
        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(tmpTokenResponse, "&");

        String accessToken = StringUtils.substringAfterLast(items[0], "=");
        Long expiresIn = new Long(StringUtils.substringAfterLast(items[1], "="));
        String refreshToken = StringUtils.substringAfterLast(items[2], "=");

        Set<String> scopes = new LinkedHashSet<>(authorizationExchange.getAuthorizationRequest().getScopes());
        Map<String,Object> additionalParameters = new LinkedHashMap<>();

        OAuth2AccessToken.TokenType accessTokenType = OAuth2AccessToken.TokenType.BEARER;
        return OAuth2AccessTokenResponse.withToken(accessToken)
                .tokenType(accessTokenType)
                .expiresIn(expiresIn)
                .refreshToken(refreshToken)
                .scopes(scopes)
                .additionalParameters(additionalParameters)
                .build();

    }
}
