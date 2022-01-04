package com.example.demo.login.api.dto.response

import com.example.demo.common.dto.response.IResponseResultBody

/**
 * 로그인 응답
 *
 * @author demo
 * @since 2021-12-27
 * @see {@link IResponseResultBody}
 */
data class ResponseToken(
    val accessToken: String,
    val expiresIn: Int,
    val refreshExpiresIn: Int,
    val refreshToken: String
): IResponseResultBody
