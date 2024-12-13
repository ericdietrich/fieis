package br.com.projeto.api.transacao;


import br.com.projeto.api.categoria.CategoriaEntity;
import br.com.projeto.api.fiel.FielEntity;
import br.com.projeto.api.fiel.FielRequestPayload;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transacao")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne //muitas transações para um fiel
    @JoinColumn(name = "fiel_id", nullable = false )
    private FielEntity fiel;

    @ManyToOne //muitas transacoes para uma categoria
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaEntity categoria;

    public TransacaoEntity(TransacaoRequestPayload data, FielEntity fiel, CategoriaEntity categoria) {
        this.valor = data.valor();
        this.data = data.data();
        this.fiel = fiel;
        this.categoria = categoria;
    }
}
