package com.example.demo.login.application

import com.example.demo.CommonControllerTest
import com.example.demo.login.application.request.RequestLogin
import org.junit.jupiter.api.DisplayName
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
    
    @Test
    @DisplayName("로그인 테스트")
    fun testLogin() {

        val id = "user1"
        val pw = "secret"

        val content = this.convertToJsonString(RequestLogin(id, pw))

        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/api/login")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}