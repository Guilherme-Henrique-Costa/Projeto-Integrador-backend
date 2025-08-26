package com.example.voluntariadointeligentehub.dto;

import com.example.voluntariadointeligentehub.entities.Voluntario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private Voluntario voluntario;
}
