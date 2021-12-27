package com.example.demo.config.keycloak.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Keycloak 로그인 응답
 *
 * @author demo
 * @since 2021-12-27
 */
data class ResponseToken(
    @JsonProperty("access_token")
    val accessToken: String,

    @JsonProperty("expires_in")
    val expiresIn: Int,

    @JsonProperty("refresh_expires_in")
    val refreshExpiresIn: Int,

    @JsonProperty("refresh_token")
    val refreshToken: String,

    @JsonProperty("token_type")
    val tokenType: String,

    @JsonProperty("not-before-policy")
    val notBeforePolicy: Int,

    @JsonProperty("session_state")
    val sessionState: String,

    val scope: String
)