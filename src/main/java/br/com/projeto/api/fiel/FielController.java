package br.com.projeto.api.fiel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fieis")
public class FielController {

    private static final Logger log = LoggerFactory.getLogger(FielController.class);
    @Autowired
    FielRepository fielRepository;

    //TODO - FAZER POST E CRIAR FIEIS DE TESTE - OK
    //TODO - FAZER LISTAGEM DE FIEIS - OK
    //TODO - ATUALIZAR DADOS DO FIEL - OK
    //TODO - EXCLUIR UM FIEL - OK
    //TODO - VALIDAR SE JÁ EXISTE CPF CADASTRADO - OK
    //TODO - EXCLUIR UM FIEL - OK
    //TODO - TRATAR MASCARA NO RETORNO DO CPF
        // @GetMapping              - OK
        // @PostMapping             - OK
        // @PutMapping("/{id}")     - OK
        // @GetMapping("/{id}")
    //TODO - TRATAR TIPO DO RETORNO QUANDO CPF JÁ EXISTE
    //TODO - CRIAR DIZIMO/OFERTA

    @GetMapping
    ResponseEntity<List<FielCreateResponse>> getFielList() {
        List<Fiel> fielList = fielRepository.findAll();
        List<FielCreateResponse> responseList = fielList.stream().map(FielCreateResponse::new).toList();
        return ResponseEntity.ok(responseList);
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
        FielCreateResponse response = new FielCreateResponse(fiel);
        log.info("createFiel: {}", response.cpf());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    ResponseEntity<FielCreateResponse> updateFiel(@PathVariable Long id, @RequestBody FielRequestPayload payload) {
        Optional<Fiel> fiel = fielRepository.findById(id);
        if (fiel.isPresent()) {
            Fiel fielUpdate = new Fiel(payload);
            fielUpdate.setId(id);
            fielRepository.save(fielUpdate);
            FielCreateResponse response = new FielCreateResponse(fielUpdate);
            return ResponseEntity.ok(response);
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




