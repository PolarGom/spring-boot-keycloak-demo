package com.example.demo.login.port.out

import com.example.demo.auth.out.dto.response.ResponseOAuthToken

/**
 * 인증 포트
 *
 * @author demo
 * @since 2021-12-24
 */
interface IOAuthPort {

    /**
     * 로그인
     *
     * @param id 사용자 아이디
     * @param pw 사용자 비밀번호
     * @return result OAuth 토큰
     */
    fun login(id: String, pw: String): ResponseOAuthToken

    /**
     * 토큰 갱신
     *
     * @param refreshToken 갱신 토큰
     * @return result OAuth 토큰
     */
    fun refreshToken(refreshToken: String): ResponseOAuthToken
}