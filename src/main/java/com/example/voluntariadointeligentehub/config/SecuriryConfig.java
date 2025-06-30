// Atualizado SecurityConfig.java com correções para CORS + CSRF + OPTIONS
package com.example.voluntariadointeligentehub.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecuriryConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/voluntario").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/vagasInstituicao").permitAll()
                        .requestMatchers(
                                "/api/v1/instituicao/**",
                                "/api/v1/voluntario/login",
                                "/api/v1/instituicao/login",
                                "/api/v1/voluntario/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/api/v1/vagasInstituicao",
                                "/api/v1/instituicao",
                                "/api/v1/vagasDisponiveis",
                                "/api/v1/candidaturas",
                                "/api/v1/vagasCandidatadas/**",
                                "/api/v1/candidaturas/voluntario/**",
                                "/api/v1/mensagem-voluntaria/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(form -> form.disable());

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}