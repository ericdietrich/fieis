package br.com.projeto.api.user;

import br.com.projeto.api.exception.ResourceAlreadyExistsException;
import br.com.projeto.api.fiel.FielEntity;
import br.com.projeto.api.utils.CpfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public UserDetails register(UserEntity user) {
        UserDetails userConsulta = userRepository.findByLogin(user.getLogin());

        if (userConsulta != null) {
            throw new ResourceAlreadyExistsException("Usuário já cadastrado com esse login: " + user.getLogin() );
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }
}
