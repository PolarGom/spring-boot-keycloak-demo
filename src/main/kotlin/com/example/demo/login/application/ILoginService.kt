package com.example.demo.login.application

import com.example.demo.common.dto.response.ResponseResult
import com.example.demo.login.api.request.RequestLogin
import com.example.demo.login.api.response.ResponseToken

/**
 * 로그인 서비스 인터페이스
 *
 * @author demo
 * @since 2021-12-27
 */
interface ILoginService {

    fun login(requestLogin: RequestLogin): ResponseResult<ResponseToken>

    fun refreshToken(refreshToken: String): ResponseResult<ResponseToken>
}