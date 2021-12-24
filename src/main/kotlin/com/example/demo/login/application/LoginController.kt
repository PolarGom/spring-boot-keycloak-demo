package com.example.demo.login.application

import com.example.demo.config.keycloak.properties.KeycloakProperty
import com.example.demo.login.application.request.RequestLogin
import org.keycloak.admin.client.Keycloak
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 로그인 컨트롤러
 *
 * @author demo
 * @since 2021-12-23
 */
@RestController
@RequestMapping("/api")
class LoginController {

    private val log = LoggerFactory.getLogger(LoginController::class.java)

    private val keycloakProperty: KeycloakProperty

    constructor(keycloakProperty: KeycloakProperty) {

        this.keycloakProperty = keycloakProperty
    }

    @PostMapping("/login")
    fun login(@RequestBody requestLogin: RequestLogin) {

        log.info("로그인 정보: $requestLogin   $keycloakProperty")


    }
}