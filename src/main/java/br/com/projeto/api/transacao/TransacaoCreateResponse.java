package br.com.projeto.api.transacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransacaoCreateResponse(Long transacaoId, BigDecimal valor, LocalDate data, Long fielId, Long categoriaId) {
    public TransacaoCreateResponse(TransacaoEntity transacao) {
        this(transacao.getId(), transacao.getValor(), transacao.getData(), transacao.getFiel().getId(), transacao.getCategoria().getId());
    }
}
