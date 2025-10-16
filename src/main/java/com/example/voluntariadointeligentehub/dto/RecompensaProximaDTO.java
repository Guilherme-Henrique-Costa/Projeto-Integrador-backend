package com.example.voluntariadointeligentehub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecompensaProximaDTO {
    private String titulo;
    private int progresso; // de 0 a 100
}
