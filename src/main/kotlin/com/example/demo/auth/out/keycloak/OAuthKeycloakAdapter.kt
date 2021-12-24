package com.example.demo.auth.out.keycloak

import com.example.demo.login.port.out.IOAuthPort
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

/**
 * 인증 Keycloak 어댑터
 *
 * @author demo
 * @since 2021-12-24
 */
@Component
class OAuthKeycloakAdapter: IOAuthPort {

    private val restTemplate: RestTemplate

    constructor(restTemplate: RestTemplate) {

        this.restTemplate = restTemplate
    }

    override fun login(id: String, pw: String) {
    }
}