package br.com.projeto.api.Fiel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FielRepository extends JpaRepository<Fiel, Long> {
    Optional<Fiel> findByCpf(String cpf);
}
