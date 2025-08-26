package com.example.voluntariadointeligentehub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginInstituicaoResponseDTO {
    private String token;
    private Long id;
    private String nome;
}
