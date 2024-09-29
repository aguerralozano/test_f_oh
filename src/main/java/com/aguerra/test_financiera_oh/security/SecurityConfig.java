package com.aguerra.test_financiera_oh.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Clase para manejo de Autenticación
 * 
 * @author Angel Guerra
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
        .csrf(AbstractHttpConfigurer::disable) //desactivar la protección contra Cross-Site Request Forgery (CSRF)
        .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui/index.html").permitAll() // Permitir acceso a Swagger
                .requestMatchers(HttpMethod.GET, "/api/v1/estudiante/**").permitAll()
                .anyRequest().authenticated()
		)
        .httpBasic(Customizer.withDefaults());

		return http.build();
	}

}
