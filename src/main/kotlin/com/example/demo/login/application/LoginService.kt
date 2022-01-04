package com.example.demo.login.application

import com.example.demo.auth.out.keycloak.OAuthKeycloakAdapter
import com.example.demo.common.dto.response.ResponseResult
import com.example.demo.login.api.dto.request.RequestLogin
import com.example.demo.login.api.dto.response.ResponseToken
import com.example.demo.login.port.out.IOAuthPort
import org.springframework.stereotype.Service

/**
 * 로그인 서비스
 *
 * @author demo
 * @since 2021-12-27
 */
@Service
class LoginService: ILoginService {

    private val oAuthAdapter: IOAuthPort

    constructor(oAuthAdapter: OAuthKeycloakAdapter) {

        this.oAuthAdapter = oAuthAdapter
    }

    override fun login(requestLogin: RequestLogin): ResponseResult<ResponseToken> {

        val responseOAuthLogin = this.oAuthAdapter.login(requestLogin.id, requestLogin.pw)

        return ResponseResult(true, "", ResponseToken(
            responseOAuthLogin.accessToken,
            responseOAuthLogin.expiresIn,
            responseOAuthLogin.refreshExpiresIn,
            responseOAuthLogin.refreshToken
        ))
    }

    override fun refreshToken(refreshToken: String): ResponseResult<ResponseToken> {

        val responseOAuthLogin = this.oAuthAdapter.refreshToken(refreshToken)

        return ResponseResult(true, "", ResponseToken(
            responseOAuthLogin.accessToken,
            responseOAuthLogin.expiresIn,
            responseOAuthLogin.refreshExpiresIn,
            responseOAuthLogin.refreshToken
        ))
    }
}