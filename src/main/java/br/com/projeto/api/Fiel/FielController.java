package br.com.projeto.api.Fiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/fieis")
public class FielController {

    @Autowired
    FielRepository fielRepository;

    //TODO - FAZER POST E CRIAR FIEIS DE TESTE - OK
    //TODO - FAZER LISTAGEM DE FIEIS - OK
    //TODO - ATUALIZAR DADOS DO FIEL - OK
    //TODO - EXCLUIR UM FIEL - OK
    //TODO - VALIDAR SE JÁ EXISTE CPF CADASTRADO - OK
    //TODO - EXCLUIR UM FIEL - OK
    //TODO - TRATAR MASCARA NO RETORNO DO CPF

    @GetMapping
    ResponseEntity<Iterable<Fiel>> getFielList() {
        Iterable<Fiel> fielList = fielRepository.findAll();
        return ResponseEntity.ok(fielList);
    }

    @GetMapping("/{id}")
    ResponseEntity<Fiel> getFielDetais(@PathVariable Long id) {
        Optional<Fiel> fiel = fielRepository.findById(id);
        return fiel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<FielCreateResponse> createFiel(@RequestBody FielRequestPayload payload) {
        Optional<Fiel> fielConsulta = fielRepository.findByCpf(payload.cpf());
         if (fielConsulta.isPresent()) {
            return ResponseEntity.notFound().build();
         }
        Fiel fiel = new Fiel(payload);
        fielRepository.save(fiel);
        return ResponseEntity.ok( new FielCreateResponse(fiel.getId()));
    }

    @PutMapping("/{id}")
    ResponseEntity<Fiel> updateFiel(@PathVariable Long id, @RequestBody FielRequestPayload payload) {
        Optional<Fiel> fiel = fielRepository.findById(id);
        if (fiel.isPresent()) {
            Fiel fielUpdate = new Fiel(payload);
            fielUpdate.setId(id);
            fielRepository.save(fielUpdate);
            return ResponseEntity.ok(fielUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteFiel(@PathVariable Long id) {
        if (fielRepository.findById(id).isPresent()) {
            fielRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}




