package com.example.voluntariadointeligentehub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecomendacaoModelDTO {
    private List<VagaRecomendadaDTO> vagasRecomendadas;
    private List<RecompensaProximaDTO> recompensasProximas;
    private List<CausaEngajadaDTO> causasMaisEngajadas;
}
