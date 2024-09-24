package br.com.projeto.api.fiel;

import br.com.projeto.api.utils.CpfUtils;

public record FielCreateResponse(Long fielId, String nome, String cpf, String email) {
    public FielCreateResponse(Fiel fiel) {
        this(fiel.getId(), fiel.getNome(), CpfUtils.formatCpf(fiel.getCpf()), fiel.getEmail());
    }
}
