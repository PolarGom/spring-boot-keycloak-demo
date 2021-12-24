package com.example.demo.config.keycloak

import com.example.demo.config.keycloak.properties.KeycloakProperty
import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.http.HttpHeaders
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import javax.ws.rs.core.MediaType

/**
 * Keycloak 클라이언트
 *
 * @author demo
 * @since 2021-12-24
 * @see {@link InitializingBean} {@link DisposableBean}
 */
class KeycloakClient: InitializingBean, DisposableBean {

    private val webclient: WebClient

    private val keycloakProperty: KeycloakProperty

    constructor(keycloakProperty: KeycloakProperty) {

        this.keycloakProperty = keycloakProperty

        val httpClient = HttpClient.create()
            .tcpConfiguration {
                it.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60 * 1000)
                    .doOnConnected {
                        it -> it.addHandler(ReadTimeoutHandler(60)).addHandler(WriteTimeoutHandler(60))
                    }
            }

        this.webclient = WebClient.builder()
            .baseUrl(this.keycloakProperty.getAuthUrl())
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
            .build()
    }

    override fun afterPropertiesSet() {
    }

    override fun destroy() {
    }
}