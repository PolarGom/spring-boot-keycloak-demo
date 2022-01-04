package com.example.demo.login.api

import com.example.demo.common.dto.response.IResponseResultBody
import com.example.demo.common.dto.response.ResponseResult
import com.example.demo.login.api.dto.request.RequestLogin
import com.example.demo.login.application.ILoginService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
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

    private val loginService: ILoginService

    constructor(loginService: ILoginService) {

        this.loginService = loginService
    }

    @PostMapping("/login")
    fun login(@RequestBody requestLogin: RequestLogin): ResponseEntity<ResponseResult<IResponseResultBody>> {

        log.info("로그인 정보: $requestLogin")

        return ResponseEntity.ok(loginService.login(requestLogin))
    }

    @PostMapping("/token-refresh")
    fun tokenRefresh(@RequestBody requestRefreshToken: String): ResponseEntity<ResponseResult<IResponseResultBody>> {

        log.info("갱신 토큰: $requestRefreshToken")

        return ResponseEntity.ok(loginService.refreshToken(requestRefreshToken))
    }

}