package com.cus.client3.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MainController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private  Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OAuth2AuthorizedClientService auth2AuthorizedClientService;

    @GetMapping("/")
    public String index(Model model, OAuth2AuthenticationToken authentication) {

        OAuth2AuthorizedClient auth2AuthorizedClient = this.getAuth2AuthorizedClient(authentication);
        model.addAttribute("userName",authentication.getName());
        model.addAttribute("clientName",auth2AuthorizedClient.getClientRegistration().getClientName());

        return "index";
    }

    @GetMapping("/login/oauth2")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是："+ targetUrl);
        }
        return "login";
    }

    private OAuth2AuthorizedClient getAuth2AuthorizedClient(OAuth2AuthenticationToken authentication) {
        return this.auth2AuthorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),authentication.getName());
    }
}
