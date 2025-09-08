package com.example.voluntariadointeligentehub.jwt;

import com.example.voluntariadointeligentehub.entities.Admin;
import com.example.voluntariadointeligentehub.repositories.AdminRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtAdminDetailsService implements UserDetailsService {


    private final AdminRepository adminRepository;


    public JwtAdminDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin não encontrado"));
        String role = "ADMIN";
        return User.builder()
                .username(admin.getEmail())
                .password(admin.getSenha())
                .roles(role)
                .build();
    }
}
