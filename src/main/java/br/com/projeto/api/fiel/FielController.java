package br.com.projeto.api.fiel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fieis")
public class FielController {

    private static final Logger log = LoggerFactory.getLogger(FielController.class);

    @Autowired
    FielRepository fielRepository;

    @Autowired
    private FielService fielService;

    //TODO - FAZER POST E CRIAR FIEIS DE TESTE - OK
    //TODO - FAZER LISTAGEM DE FIEIS - OK
    //TODO - ATUALIZAR DADOS DO FIEL - OK
    //TODO - EXCLUIR UM FIEL - OK
    //TODO - VALIDAR SE JÁ EXISTE CPF CADASTRADO - OK
    //TODO - EXCLUIR UM FIEL - OK
    //TODO - TRATAR MASCARA NO RETORNO DO CPF - OK
        // @GetMapping              - OK
        // @PostMapping             - OK
        // @PutMapping("/{id}")     - OK
        // @GetMapping("/{id}")     - OK
    //TODO - CRIAR CAMADA DE EXCEPTIONS (criando camada de serviço para criar exceptions personalizadas) - OK
    //TODO - ENVIAR PARA CAMADA DE SERVICOS -
        // @GetMapping              - OK
        // @GetMapping("/{id}")     - OK
        // @PostMapping             - OK
        // @PutMapping("/{id}")     -
    //TODO - TRATAR TIPO DO RETORNO QUANDO CPF JÁ EXISTE
        // @PostMapping             - OK
    //TODO - CRIAR DIZIMO/OFERTA


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
        Optional<FielEntity> fiel = fielRepository.findById(id);
        if (fiel.isPresent()) {
            FielEntity fielUpdate = new FielEntity(payload);
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




