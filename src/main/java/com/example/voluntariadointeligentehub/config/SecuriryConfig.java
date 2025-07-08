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
                .cors(cors -> {}) // Habilita CORS
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para facilitar testes com APIs REST
                .authorizeHttpRequests(auth -> auth
                        // Permite requisições OPTIONS para CORS
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Permissões específicas por método
                        .requestMatchers(HttpMethod.POST, "/api/v1/voluntario").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/vagasInstituicao").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/instituicao/login").permitAll()

                        // Permissões gerais
                        .requestMatchers(
                                "/api/v1/voluntario/**",
                                "/api/v1/instituicao/**",
                                "/api/v1/vagasInstituicao/**",
                                "/api/v1/vagasDisponiveis",
                                "/api/v1/vagasCandidatadas/**",
                                "/api/v1/candidaturas/**",
                                "/api/v1/candidaturas/registrar/**",
                                "/api/v1/candidaturas/cancelar/**",
                                "/api/v1/candidaturas/voluntario/**",
                                "/api/v1/candidaturas/vaga/**",
                                "/api/v1/mensagem-voluntaria/**",
                                "/api/v1/perfil-instituicao/**",
                                "/api/v1/perfil-instituicao/email",
                                "/api/v1/vagasInstituicao/com-candidatos/**",

                                // Swagger
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Qualquer outra rota requer autenticação
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // Desativa autenticação básica
                .formLogin(form -> form.disable()); // Desativa formulário padrão

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*")); // Permite todas as origens (ajuste se necessário)
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
