package br.com.projeto.api.transacao;

import br.com.projeto.api.fiel.FielEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Long> {

    List<TransacaoEntity> findByFiel(FielEntity fiel);
}
