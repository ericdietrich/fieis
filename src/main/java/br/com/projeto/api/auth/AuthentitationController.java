package br.com.projeto.api.auth;

import br.com.projeto.api.user.RegisterDTO;
import br.com.projeto.api.user.UserEntity;
import br.com.projeto.api.user.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("auth")
public class AuthentitationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        log.info("{} {}", data.login(), data.password());
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        log.info("{}", usernamePassword.isAuthenticated());
        //var auth = this.authenticationManager.authenticate(usernamePassword); ******* ESSE TRECHO ESTÁ DANDO STACKOVERFLOW
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.userRepository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryotedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserEntity user = new UserEntity(data.login(), encryotedPassword, data.role());
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
