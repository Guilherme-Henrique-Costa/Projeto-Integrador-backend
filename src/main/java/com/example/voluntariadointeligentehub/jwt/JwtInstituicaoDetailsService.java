package com.example.voluntariadointeligentehub.jwt;

import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.repositories.InstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class JwtInstituicaoDetailsService implements UserDetailsService {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Instituicao instituicao = instituicaoRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Instituição não encontrada"));

        return User.builder()
                .username(instituicao.getEmail())
                .password(instituicao.getSenha())
                .roles("INSTITUICAO")
                .build();
    }
}
