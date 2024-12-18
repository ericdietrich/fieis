package br.com.projeto.api.fiel;

import br.com.projeto.api.transacao.TransacaoCreateResponse;
import br.com.projeto.api.transacao.TransacaoEntity;
import br.com.projeto.api.transacao.TransacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fieis")
public class FielController {

    private static final Logger log = LoggerFactory.getLogger(FielController.class);

    @Autowired
    private FielService fielService;
    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    ResponseEntity<List<FielCreateResponse>> getFielList() {
        List<FielEntity> fielList = fielService.findAll();
        List<FielCreateResponse> responseList = fielList.stream().map(FielCreateResponse::new).toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    ResponseEntity<FielCreateResponse> getFielDetais(@PathVariable Long id) {
        FielEntity fiel = fielService.findById(id);
        FielCreateResponse response = new FielCreateResponse(fiel);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<FielCreateResponse> createFiel(@RequestBody FielRequestPayload payload) {
        FielEntity fiel = new FielEntity(payload);
        FielEntity fielSaldo = fielService.save(fiel);
        FielCreateResponse response = new FielCreateResponse(fielSaldo);
        log.info("createFiel: {}", response.cpf());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    ResponseEntity<FielCreateResponse> updateFiel(@PathVariable Long id, @RequestBody FielRequestPayload payload) {
        FielEntity fiel = new FielEntity(payload);
        fiel.setId(id);
        FielEntity fielUpdate = fielService.update(fiel);
        FielCreateResponse response = new FielCreateResponse(fielUpdate);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteFiel(@PathVariable Long id) {
        fielService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/transacoes")
    ResponseEntity<List<TransacaoCreateResponse>> getFielTransacoes(@PathVariable Long id) {
        FielEntity fiel = fielService.findById(id);
        List<TransacaoEntity> transacaoList = transacaoService.findByFiel(fiel);
        List<TransacaoCreateResponse> responseList = transacaoList.stream().map(TransacaoCreateResponse::new).toList();
        return ResponseEntity.ok(responseList);
    }
}




