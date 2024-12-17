package br.com.projeto.api.transacao;

import br.com.projeto.api.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    public List<TransacaoEntity> findAll() {
        return transacaoRepository.findAll();
    }

    public TransacaoEntity findById(Long id) {
        return transacaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Transacao não encontrado com o id: " + id));
    }

    public TransacaoEntity createTransacao(TransacaoEntity transacao) {
        return transacaoRepository.save(transacao);
    }

    public TransacaoEntity update(TransacaoEntity transacao) {
        transacaoRepository.findById(transacao.getId()).orElseThrow(() -> new EntityNotFoundException("Transacao não encontrado com o id: " + transacao.getId()));
        return transacaoRepository.save(transacao);
    }
}
