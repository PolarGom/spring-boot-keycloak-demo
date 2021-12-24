package com.example.demo.login.port.out

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
     */
    fun login(id: String, pw: String)
}