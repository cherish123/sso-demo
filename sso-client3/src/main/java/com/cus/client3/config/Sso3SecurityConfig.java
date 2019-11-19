package com.cus.client3.config;

import com.cus.client3.registrations.provider.CompositeOAuth2AccessTokenResponseClient;
import com.cus.client3.registrations.provider.CompositeOAuth2UserService;
import com.cus.client3.registrations.qq.QQOAuth2AccessTokenResponseClient;
import com.cus.client3.registrations.qq.QQOAuth2UserService;
import com.cus.client3.registrations.qq.QQUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

@EnableWebSecurity //(debug = true)
public class Sso3SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(Sso3SecurityConfig.class);

    public static final String QQRegistrationId = "callback";

    public static final String loginPagePath = "/login/oauth2";


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("Sso3SecurityConfig中配置HttpSecurity对象执行");
            http.authorizeRequests()
//            .antMatchers("/client2/me").hasRole("ADMIN")
            .antMatchers(loginPagePath).permitAll()
            .anyRequest()
            .authenticated();
            http.oauth2Login()
                    .tokenEndpoint()
                        .accessTokenResponseClient(this.accessTokenResponseClient())
                        .and()
                    .userInfoEndpoint()
                        .customUserType(QQUserInfo.class,QQRegistrationId)
                        .userService(oAuth2UserService())
                        .and()
                    .loginPage(loginPagePath)
                    .redirectionEndpoint().baseUri("/qqLogin/*")
                    ;
    }

    private OAuth2AccessTokenResponseClient accessTokenResponseClient() {
        CompositeOAuth2AccessTokenResponseClient client = new CompositeOAuth2AccessTokenResponseClient();
        //加入QQ自定义QQOAuth2AccessTokenResponseClient
        client.getOAuth2AccessTokenResponseClients().put(QQRegistrationId,new QQOAuth2AccessTokenResponseClient());
        return client;
    }

    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        CompositeOAuth2UserService service = new CompositeOAuth2UserService();
        //加入QQ自定义QQOAuth2UserService
        service.getUserServices().put(QQRegistrationId,new QQOAuth2UserService());
        return service;
    }
}
