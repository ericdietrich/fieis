package br.com.projeto.api.transacao;

import br.com.projeto.api.categoria.CategoriaService;
import br.com.projeto.api.fiel.FielService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private static final Logger log = LoggerFactory.getLogger(TransacaoController.class);
    @Autowired
    TransacaoService transacaoService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    FielService fielService;

    @GetMapping
    public ResponseEntity<List<TransacaoCreateResponse>> getTransacaoList() {
        List<TransacaoEntity> transacaoList = transacaoService.findAll();
        List<TransacaoCreateResponse> responseList = transacaoList.stream().map(t -> {
            return new TransacaoCreateResponse(t.getId(), t.getValor(), t.getData(), t.getFiel().getId(), t.getCategoria().getId());
        }).toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoCreateResponse> getTransacaoById(@PathVariable Long id) {
        TransacaoEntity transacao = transacaoService.findById(id);
        TransacaoCreateResponse response = new TransacaoCreateResponse(transacao);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TransacaoCreateResponse> createTransacao(@RequestBody TransacaoRequestPayload payload) {
        log.info("createTransacao: {}", payload);
        TransacaoEntity transacao = new TransacaoEntity(payload, fielService.findById(payload.fielId()), categoriaService.findById(payload.categoriaId()));
        TransacaoEntity transacaoSalva = transacaoService.createTransacao(transacao);
        TransacaoCreateResponse response = new TransacaoCreateResponse(transacaoSalva);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoCreateResponse> updateTransacao(@RequestBody TransacaoRequestPayload payload, @PathVariable Long id) {
        TransacaoEntity transacaoEncontrada = transacaoService.findById(id);
        TransacaoEntity transacaoAtualizada = new TransacaoEntity(payload, transacaoEncontrada.getFiel(), transacaoEncontrada.getCategoria());
        transacaoAtualizada.setId(transacaoEncontrada.getId());
        transacaoAtualizada = transacaoService.update(transacaoAtualizada);
        TransacaoCreateResponse response = new TransacaoCreateResponse(transacaoAtualizada);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransacao(@PathVariable Long id) {
        transacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
