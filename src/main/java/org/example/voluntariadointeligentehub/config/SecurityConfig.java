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
        http.cors().and().csrf().disable()  // Desabilita CSRF e ativa CORS
                .authorizeRequests()

                // Endpoints públicos (acessíveis sem autenticação)
                .antMatchers(
                        "/api/v1/instituicao/login",   // Login de instituição
                        "/api/v1/voluntario/login",    // Login de voluntário
                        "/api/vagasInstituicao/**",    // Endpoints de vagas acessíveis a todos
                        "/api/voluntarios",            // Endpoints de voluntários acessíveis a todos
                        "/api/v1/perfil-instituicao/**",  // Endpoint de Perfil instituição
                        "/api/vagas",                  // Endpoint de vagas
                        "/api/candidaturas/**"            // Endpoint de candidaturas
                ).permitAll()  // Permitir acesso a estas rotas sem autenticação

                // Endpoints que exigem autenticação
                .antMatchers().authenticated()

                // Qualquer outra rota não mencionada exige autenticação
                .anyRequest().authenticated();

        return http.build();
    }
}
