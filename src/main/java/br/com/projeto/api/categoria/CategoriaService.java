package br.com.projeto.api.categoria;

import br.com.projeto.api.exception.EntityNotFoundException;
import br.com.projeto.api.exception.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<CategoriaEntity> findAll() {
        return  categoriaRepository.findAll();
    }

    public CategoriaEntity findById(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o id "+ id));
    }

    public CategoriaEntity save(CategoriaEntity categoria) {
        Optional<CategoriaEntity> categoriaSalva = categoriaRepository.findByNomeIgnoreCase(categoria.getNome());
        if(categoriaSalva.isPresent()) {
            throw new ResourceAlreadyExistsException("Categoria já cadastrada com o nome: " + categoria.getNome());
        }
        return categoriaRepository.save(categoria);
    }

    public CategoriaEntity update(CategoriaEntity categoria) {
        CategoriaEntity categoriaSalva = categoriaRepository.findById(categoria.getId()).orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o id "+ categoria.getId()));
        return categoriaRepository.save(categoria);
    }
}
