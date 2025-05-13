package org.example.voluntariadointeligentehub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/instituicao/login",
                                "/api/v1/voluntario/login",
                                "/api/vagasInstituicao/**",
                                "/api/voluntarios",
                                "/api/v1/perfil-instituicao/**",
                                "/api/vagas",
                                "/api/candidaturas/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
