package com.example.voluntariadointeligentehub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoluntarioEstatisticasDTO {
    private long totalCandidaturas;
    private long totalConcluidas;
    private long totalInstituicoesAtuou;
}