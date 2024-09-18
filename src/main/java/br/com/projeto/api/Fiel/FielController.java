package br.com.projeto.api.Fiel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/fieis")
public class FielController {

    private static final Logger log = LoggerFactory.getLogger(FielController.class);
    @Autowired
    FielRepository fielRepository;

    //TODO - FAZER POST E CRIAR FIEIS DE TESTE - OK
    //TODO - FAZER LISTAGEM DE FIEIS
    //TODO - ATUALIZAR DADOS DO FIEL
    //TODO - EXCLUIR UM FIEL
    //TODO - VALIDAR SE JÁ EXISTE CPF CADASTRADO


    @GetMapping("id")
    ResponseEntity<Fiel> getFielDetais(@PathVariable Long id) {
        Optional<Fiel> fiel = fielRepository.findById(id);
        return fiel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<FielCreateResponse> createFiel(@RequestBody FielRequestPayload payload) {
        Fiel fiel = new Fiel(payload);
        fielRepository.save(fiel);
        log.info("Salvando fiel " + fiel.getNome());
        return ResponseEntity.ok( new FielCreateResponse(fiel.getId()));
    }
}


