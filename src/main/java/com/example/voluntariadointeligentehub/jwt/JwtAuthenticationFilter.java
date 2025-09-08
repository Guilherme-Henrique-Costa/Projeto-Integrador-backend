package com.example.voluntariadointeligentehub.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Locale;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired private JwtUtil jwtUtil;
    @Autowired private JwtUserDetailsService userDetailsService;
    @Autowired private JwtInstituicaoDetailsService instituicaoDetailsService;
    @Autowired private JwtAdminDetailsService adminDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {


        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);
        final String email = jwtUtil.getEmailFromToken(token);
        String tipo = jwtUtil.getTipoFromToken(token);
        if (tipo != null) tipo = tipo.toUpperCase(Locale.ROOT);


        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails;
            if ("VOLUNTARIO".equals(tipo)) {
                userDetails = userDetailsService.loadUserByUsername(email);
            } else if ("INSTITUICAO".equals(tipo)) {
                userDetails = instituicaoDetailsService.loadUserByUsername(email);
            } else if ("ADMIN".equals(tipo)) {
                userDetails = adminDetailsService.loadUserByUsername(email);
            } else {
                filterChain.doFilter(request, response);
                return;
            }


            if (jwtUtil.isTokenValido(token)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
