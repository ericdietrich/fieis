package br.com.projeto.api.fiel;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        this.cpf = data.cpf().replace("-", "").replace(".", "");
        this.email = data.email();
    }
}
