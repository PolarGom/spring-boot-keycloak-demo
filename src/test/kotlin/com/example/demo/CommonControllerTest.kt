package com.example.demo

import com.example.demo.common.dto.response.IResponseResultBody
import com.example.demo.common.dto.response.ResponseResult
import com.example.demo.login.api.response.ResponseToken
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult

/**
 * 공통 컨트롤러 테스트
 * 
 * @author demo
 * @since 2021-12-23
 */
@SpringBootTest
@AutoConfigureMockMvc
class CommonControllerTest {

	@Autowired
	protected lateinit var mockMvc: MockMvc

	@Autowired
	protected lateinit var objectMapper: ObjectMapper

	protected fun convertToJsonString(value: Any): String {

		return objectMapper.writeValueAsString(value)
	}

	protected fun <T: IResponseResultBody> parseMvcRespnoseBody(mvcResult: MvcResult, parseObject: Class<T>): T {

		val body = this.objectMapper.readValue(mvcResult.response.contentAsString, Map::class.java)["body"]

		return this.objectMapper.convertValue(body, parseObject)
	}
}
