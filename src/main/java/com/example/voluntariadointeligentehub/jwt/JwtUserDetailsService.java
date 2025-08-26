package com.example.voluntariadointeligentehub.jwt;

import com.example.voluntariadointeligentehub.entities.Voluntario;
import com.example.voluntariadointeligentehub.repositories.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Voluntario voluntario = voluntarioRepository.findByEmailInstitucional(email)
                .orElseThrow(() -> new UsernameNotFoundException("Voluntário não encontrado"));

        return User.builder()
                .username(voluntario.getEmailInstitucional())
                .password(voluntario.getSenha())
                .roles("VOLUNTARIO") // pode adicionar lógica se houver tipo de usuário
                .build();
    }
}
