//package com.cus.client4.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity(debug = true)
//public class Sso4SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private static final Logger logger = LoggerFactory.getLogger(Sso4SecurityConfig.class);
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        logger.info("Sso3SecurityConfig中配置HttpSecurity对象执行");
//            http.authorizeRequests()
////            .antMatchers("/client2/me").hasRole("ADMIN")
//            .antMatchers("/login/oauth2").permitAll()
//            .anyRequest()
//            .authenticated();
////            http.oauth2Login()
////                    .loginPage("/login/oauth2")
////                    .redirectionEndpoint().baseUri("/login/oauth2/code/*")        ;
//    }
//}