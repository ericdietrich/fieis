package br.com.projeto.api.fiel;

import br.com.projeto.api.exception.EntityNotFoundException;
import br.com.projeto.api.exception.ResourceAlreadyExistsException;
import br.com.projeto.api.utils.CpfUtils;
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
           throw new ResourceAlreadyExistsException("Fiel já cadastrado com o cpf: " + CpfUtils.formatCpf(fiel.getCpf()) );
        }
        return fielRepository.save(fiel);
    }

    public FielEntity update(FielEntity fiel) {
        fielRepository.findById(fiel.getId()).orElseThrow(() -> new EntityNotFoundException("Fiel não encontrado com o id: " + fiel.getId()));
        return fielRepository.save(fiel);
    }

    public void delete(Long id) {
        fielRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fiel não encontrado com o id: " + id));
        fielRepository.deleteById(id);
    }
}