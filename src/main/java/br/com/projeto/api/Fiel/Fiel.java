package br.com.projeto.api.Fiel;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "fiel")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Fiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private String cpf;

    @Column
    private String email;

    public Fiel(FielRequestPayload data) {
        this.nome = data.nome();
        this.cpf = data.cpf();
        this.email = data.email();
    }
}
