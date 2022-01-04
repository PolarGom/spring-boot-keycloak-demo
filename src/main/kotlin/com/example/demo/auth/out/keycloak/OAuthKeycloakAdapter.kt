package com.example.demo.auth.out.keycloak

import com.example.demo.auth.out.dto.response.ResponseOAuthToken
import com.example.demo.config.keycloak.KeycloakClient
import com.example.demo.login.port.out.IOAuthPort
import org.springframework.stereotype.Component

/**
 * 인증 Keycloak 어댑터
 *
 * @author demo
 * @since 2021-12-24
 */
@Component
class OAuthKeycloakAdapter: IOAuthPort {

    private val keycloakClient: KeycloakClient

    constructor(keycloakClient: KeycloakClient) {

        this.keycloakClient = keycloakClient
    }

    override fun login(id: String, pw: String): ResponseOAuthToken {

        val responseLogin = keycloakClient.login(id, pw)

        return ResponseOAuthToken(responseLogin!!.accessToken,
            responseLogin.expiresIn,
            responseLogin.refreshExpiresIn,
            responseLogin.refreshToken)
    }

    override fun refreshToken(refreshToken: String): ResponseOAuthToken {

        val responseLogin = keycloakClient.refreshToken(refreshToken)

        return ResponseOAuthToken(responseLogin!!.accessToken,
            responseLogin.expiresIn,
            responseLogin.refreshExpiresIn,
            responseLogin.refreshToken)
    }
}