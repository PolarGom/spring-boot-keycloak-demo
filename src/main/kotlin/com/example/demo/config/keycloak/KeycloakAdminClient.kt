package com.example.demo.config.keycloak

import com.example.demo.config.keycloak.properties.KeycloakProperty
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean

/**
 * Keycloak 클라이언트
 *
 * @author demo
 * @since 2021-12-24
 * @see {@link InitializingBean} {@link DisposableBean}
 */
class KeycloakAdminClient: InitializingBean, DisposableBean {

    private val keycloak: Keycloak

    private val keycloakProperty: KeycloakProperty

    constructor(keycloakProperty: KeycloakProperty) {

        this.keycloakProperty = keycloakProperty

        keycloak = Keycloak.getInstance(this.keycloakProperty.authServerUrl,
            this.keycloakProperty.realm,
            this.keycloakProperty.adminId,
            this.keycloakProperty.adminPw,
            this.keycloakProperty.resource
        )
    }

    override fun afterPropertiesSet() {
    }

    override fun destroy() {
    }
}