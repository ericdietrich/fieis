package br.com.projeto.api.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaCreateResponse>> getCategoriaList() {
        List<CategoriaEntity> categoriaList = categoriaService.findAll();
        List<CategoriaCreateResponse> responseList = categoriaList.stream().map(CategoriaCreateResponse::new).toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaCreateResponse> getCategoria(@PathVariable Long id) {
        CategoriaEntity categoria = categoriaService.findById(id);
        CategoriaCreateResponse response = new CategoriaCreateResponse(categoria);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CategoriaCreateResponse> saveCategoria(@RequestBody CategoriaRequestPayload payload) {
        CategoriaEntity categoria = new CategoriaEntity(payload);
        categoriaService.save(categoria);
        CategoriaCreateResponse response = new CategoriaCreateResponse(categoria);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaCreateResponse> updateCategoria(@RequestBody CategoriaRequestPayload payload, @PathVariable Long id) {
        CategoriaEntity categoria = new CategoriaEntity(payload);
        categoria.setId(id);
        categoriaService.update(categoria);
        CategoriaCreateResponse response = new CategoriaCreateResponse(categoria);
        return ResponseEntity.ok(response);
    }

}
