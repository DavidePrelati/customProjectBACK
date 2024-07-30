package davide.prelati.customProjectBACK.services;

import davide.prelati.customProjectBACK.entities.User;
import davide.prelati.customProjectBACK.exceptions.UnauthorizedException;
import davide.prelati.customProjectBACK.payloads.UserLoginDTO;
import davide.prelati.customProjectBACK.security.JWTTokenConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService utenteService;
    @Autowired
    private JWTTokenConfiguration jwtTokenConfiguration;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateUserAndGenerateToken(UserLoginDTO body) {
        User nuovoUtente = this.utenteService.findByEmail(body.email());
        if (passwordEncoder.matches(body.password(), nuovoUtente.getPassword())) {
            return jwtTokenConfiguration.createToken(nuovoUtente);
        } else {
            throw new UnauthorizedException("Credenziali non corrette!");
        }
    }
}