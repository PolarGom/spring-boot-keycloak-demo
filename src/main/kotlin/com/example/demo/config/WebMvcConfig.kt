package com.example.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Web MVC 설정
 *
 * @author demo
 * @since 2021-12-23
 */
@Configuration
class WebMvcConfig: WebMvcConfigurer {

    private val MAX_AGE_SECONDS: Long = 3600

    override fun addCorsMappings(registry: CorsRegistry) {

        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(MAX_AGE_SECONDS)
    }

    @Bean
    fun getRestTemplate(): RestTemplate {

        return RestTemplate()
    }
}