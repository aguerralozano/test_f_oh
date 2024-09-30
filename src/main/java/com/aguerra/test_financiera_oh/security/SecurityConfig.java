package com.aguerra.test_financiera_oh.security;

/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
*/

/**
 * Clase para manejo de Autenticación
 * 
 * @author Angel Guerra
 */

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    /*
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) // Desactivar CSRF
            .authorizeHttpRequests(auth -> auth
                            .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui/index.html").permitAll() // Permitir acceso a Swagger
                            .antMatchers(HttpMethod.GET, "/api/v1/estudiante/**").permitAll()
                            .anyRequest().authenticated() // Requiere autenticación para todas las demás solicitudes
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Para APIs sin estado
            )
            .httpBasic(withDefaults()); // Habilitar autenticación básica

        return http.build();   
	}
    */

}
