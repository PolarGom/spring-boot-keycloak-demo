package com.example.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Bean
    fun noOpPasswordEncoder(): PasswordEncoder {

        return NoOpPasswordEncoder.getInstance();
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {

        auth!!.inMemoryAuthentication()
            .withUser("user")
            .password("{noop}pass")
            .roles("USER")
    }

    override fun configure(http: HttpSecurity?) {

        http!!.csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeRequests().antMatchers("/oauth/**", "/oauth2/callback", "/h2-console/*").permitAll()
            .and()
            .formLogin()
            .and()
            .httpBasic()
    }
}