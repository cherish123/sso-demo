package com.cus.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder()	{
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("cus");
        return converter;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        clients.inMemory()
                .withClient("cus1")
                    .secret(passwordEncoder.encode("cus1"))
                    .redirectUris("http://127.0.0.1:8088/client1/login")
                    .refreshTokenValiditySeconds(7200)
                    .accessTokenValiditySeconds(3600)
                    .authorizedGrantTypes("authorization_code", "refresh_token")
                    .scopes("all")
                    .and()
                .withClient("cus2")
                    .secret(passwordEncoder.encode("cus2"))
                    .redirectUris("http://127.0.0.1:8089/client2/login")
                    .refreshTokenValiditySeconds(7200)
                    .accessTokenValiditySeconds(3600)
                    .authorizedGrantTypes("authorization_code", "refresh_token")
                    .scopes("all")
                    .and()
                .withClient("client-for-server")
                .secret(passwordEncoder.encode("client-for-server"))
                .redirectUris("http://127.0.0.1:8090/login/oauth2/code/authorizationserver")
                .refreshTokenValiditySeconds(7200)
                .accessTokenValiditySeconds(3600)
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .scopes("all");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()"); //获取签名key要认证
    }
}
