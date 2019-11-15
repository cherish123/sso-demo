package com.cus.client3.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity(debug = true)
public class Sso3SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(Sso3SecurityConfig.class);


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("Sso3SecurityConfig中配置HttpSecurity对象执行");
            http.authorizeRequests()
//            .antMatchers("/client2/me").hasRole("ADMIN")
            .antMatchers("/login/oauth2").permitAll()
            .anyRequest()
            .authenticated();
            http.oauth2Login()
                    .loginPage("/login/oauth2")
                    .redirectionEndpoint().baseUri("/login/oauth2/code/*")        ;
    }
}
