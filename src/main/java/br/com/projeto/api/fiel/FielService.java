package br.com.projeto.api.fiel;

import br.com.projeto.api.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FielService {

    @Autowired
    FielRepository fielRepository;

    public FielEntity findById(Long id) {
        return fielRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fiel não encontrado com o id: " + id));
    }
}