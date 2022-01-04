package com.example.demo.login.api.dto.request

/**
 * 로그인 요청
 *
 * @author demo
 * @since 2021-12-23
 */
data class RequestLogin(
    val id: String,
    val pw: String
)