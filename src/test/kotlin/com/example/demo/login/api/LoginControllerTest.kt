package com.example.demo.login.api

import com.example.demo.CommonControllerTest
import com.example.demo.login.api.dto.request.RequestLogin
import com.example.demo.login.api.dto.response.ResponseToken
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import javax.ws.rs.core.MediaType

/**
 * 로그인 컨트롤러 테스트
 *
 * @author demo
 * @since 2021-12-23
 */
class LoginControllerTest: CommonControllerTest() {

    var responseToken: ResponseToken? = null

    @Test
    @Order(1)
    @DisplayName("로그인 테스트")
    fun testLogin() {

        val id = "user1"
        val pw = "1234"

        val content = this.convertToJsonString(RequestLogin(id, pw))

        val mvcResult = this.mockMvc.perform(
            MockMvcRequestBuilders.post("/api/login")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        val responseToken: ResponseToken = parseMvcRespnoseBody(mvcResult, ResponseToken::class.java)

        this.responseToken = responseToken
    }

    @Test
    @Order(2)
    @DisplayName("토큰 갱신 테스트")
    fun testRefreshToken() {

        val mvcResult = this.mockMvc.perform(
            MockMvcRequestBuilders.post("/api/token-refresh")
                .content(this.responseToken!!.refreshToken)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
    }
}