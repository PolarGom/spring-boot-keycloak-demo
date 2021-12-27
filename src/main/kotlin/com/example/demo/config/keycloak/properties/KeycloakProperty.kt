package com.example.demo.config.keycloak.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.NestedConfigurationProperty
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

/**
 * Keycloak 설정
 *
 * @author demo
 * @since 2021-12-24
 */
@ConfigurationProperties(prefix = "keycloak")
@ConstructorBinding
data class KeycloakProperty(
    val realm: String,
    val authServerUrl: String,
    val sslRequired: String,
    val resource: String,
    val credentials: Credentials,
    val useResourceRoleMappings: Boolean,
    val bearerOnly: Boolean
)
