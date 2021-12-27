package com.example.demo.auth.out.response

/**
 * OAuth 로그인 응답
 *
 * @author demo
 * @since 2021-12-27
 */
data class ResponseOAuthToken(
    val accessToken: String,
    val expiresIn: Int,
    val refreshExpiresIn: Int,
    val refreshToken: String
)
