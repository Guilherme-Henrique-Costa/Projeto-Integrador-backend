package com.example.voluntariadointeligentehub.dto;

import java.time.LocalDateTime;

public class ConversaDTO {
    private String nomeInstituicao;
    private String ultimaMensagem;
    private LocalDateTime data;
    private boolean lido;

    public ConversaDTO(String nomeInstituicao, String ultimaMensagem, LocalDateTime data, boolean lido) {
        this.nomeInstituicao = nomeInstituicao;
        this.ultimaMensagem = ultimaMensagem;
        this.data = data;
        this.lido = lido;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public String getUltimaMensagem() {
        return ultimaMensagem;
    }

    public LocalDateTime getData() {
        return data;
    }

    public boolean isLido() {
        return lido;
    }
}
