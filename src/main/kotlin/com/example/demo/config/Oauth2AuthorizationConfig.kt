package com.example.demo.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer

@Configuration
@EnableAuthorizationServer
class Oauth2AuthorizationConfig: AuthorizationServerConfigurerAdapter() {

    override fun configure(clients: ClientDetailsServiceConfigurer?) {

        clients!!.inMemory()
            .withClient("testClientId")
            .secret("{noop}testSecret")
            .redirectUris("http://localhost:8081/oauth2/callback")
            .authorizedGrantTypes("authorization_code")
            .scopes("read", "write")
            .accessTokenValiditySeconds(30000)
    }
}