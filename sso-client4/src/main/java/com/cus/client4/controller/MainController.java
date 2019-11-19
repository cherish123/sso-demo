package com.cus.client4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private OAuth2AuthorizedClientService auth2AuthorizedClientService;

    @GetMapping("/")
    public String index(Model model, OAuth2AuthenticationToken authentication) {

        OAuth2AuthorizedClient auth2AuthorizedClient = this.getAuth2AuthorizedClient(authentication);
        model.addAttribute("userName",authentication.getName());
        model.addAttribute("clientName",auth2AuthorizedClient.getClientRegistration().getClientName());

        return "index";
    }

//    @GetMapping("/login/oauth2")
//    public String login(){
//        return "login";
//    }

    private OAuth2AuthorizedClient getAuth2AuthorizedClient(OAuth2AuthenticationToken authentication) {
        return this.auth2AuthorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),authentication.getName());
    }
}