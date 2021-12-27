package com.example.demo.config.keycloak

import com.example.demo.config.keycloak.dto.response.ResponseToken
import com.example.demo.config.keycloak.properties.KeycloakProperty
import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient

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

    private val userClientUrl: String = "/realms/{realmId}/protocol/openid-connect/token"

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
            .baseUrl(this.keycloakProperty.authServerUrl)
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .build()
    }

    fun login(id: String, pw: String): ResponseToken? {

        return this.webclient.post()
            .uri(userClientUrl, this.keycloakProperty.realm)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData("client_id", this.keycloakProperty.resource)
                .with("username", id)
                .with("password", pw)
                .with("grant_type", "password")
                .with("client_secret", this.keycloakProperty.credentials.secret))
            .retrieve()
            /*.onStatus(HttpStatus::is4xxClientError) {
                it -> throw Exception(it.statusCode())
            }*/
            .bodyToMono(ResponseToken::class.java)
            .block()
    }

    fun refreshToken(refreshToken: String): ResponseToken? {

        return this.webclient.post()
            .uri(userClientUrl, this.keycloakProperty.realm)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData("client_id", this.keycloakProperty.resource)
                .with("refresh_token", refreshToken)
                .with("grant_type", "refresh_token")
                .with("client_secret", this.keycloakProperty.credentials.secret))
            .retrieve()
            /*.onStatus(HttpStatus::is4xxClientError) {
                it -> throw Exception(it.statusCode())
            }*/
            .bodyToMono(ResponseToken::class.java)
            .block()
    }

    override fun afterPropertiesSet() {
    }

    override fun destroy() {
    }
}