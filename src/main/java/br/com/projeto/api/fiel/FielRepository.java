package br.com.projeto.api.fiel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FielRepository extends JpaRepository<FielEntity, Long> {
    Optional<FielEntity> findByCpf(String cpf);
}
