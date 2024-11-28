package br.com.projeto.api.fiel;

import br.com.projeto.api.exception.EntityNotFoundException;
import br.com.projeto.api.exception.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FielService {

    @Autowired
    FielRepository fielRepository;

    public FielEntity findById(Long id) {
        return fielRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fiel não encontrado com o id: " + id));
    }

    public List<FielEntity> findAll() {
        return fielRepository.findAll();
    }

    public FielEntity save(FielEntity fiel) {
        Optional<FielEntity> fielConsulta = fielRepository.findByCpf(fiel.getCpf());

        if (fielConsulta.isPresent()) {
           throw new ResourceAlreadyExistsException("Fiel já cadastrado com o cpf: " + fiel.getCpf());
        }
        return fielRepository.save(fiel);
    }
}