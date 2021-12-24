package com.example.demo.config

import com.example.demo.config.keycloak.properties.KeycloakProperty
import org.keycloak.adapters.KeycloakConfigResolver
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy
import java.io.InputStream

/**
 * Keycloak 및 Security 설정
 *
 * @author demo
 * @since 2021-12-23
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(KeycloakProperty::class)
class KeycloakConfig: KeycloakWebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {

        super.configure(http)

        // Keycloak 에서 Client 의 Role 을 추가한다. 혹은 Realm Role 에 Composite Roles 설정을 추가하여 Client 의 Role 을 추가한다.
        http!!.authorizeRequests()
            .antMatchers("/api/anonymous").permitAll()
            .antMatchers("/api/user").hasAnyRole("user")
            .antMatchers("/api/admin").hasAnyRole("admin")
            .antMatchers("/api/all-user").hasAnyRole("user", "admin")
            .anyRequest().permitAll()
        http!!.csrf().disable()
    }

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {

        var keycloakAuthenticationProvider = keycloakAuthenticationProvider()
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(SimpleAuthorityMapper())

        auth.authenticationProvider(keycloakAuthenticationProvider)
    }

    @Bean
    override fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy {

        return RegisterSessionAuthenticationStrategy(SessionRegistryImpl())
    }

    @Bean
    fun KeycloakConfigResolver(): KeycloakConfigResolver {

        return KeycloakSpringBootConfigResolver()
    }
}
