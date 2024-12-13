package br.com.projeto.api.transacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransacaoRequestPayload(BigDecimal valor, LocalDate data, Long fielId, Long categoriaId) {
}
