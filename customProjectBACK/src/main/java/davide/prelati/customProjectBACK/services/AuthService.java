package davide.prelati.customProjectBACK.services;

import davide.prelati.customProjectBACK.entities.User;
import davide.prelati.customProjectBACK.exceptions.UnauthorizedException;
import davide.prelati.customProjectBACK.payloads.UserLoginDTO;
import davide.prelati.customProjectBACK.security.JwtTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService utenteService;

    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String generateToken(UserLoginDTO uld) {
        User utente = utenteService.findByEmail(uld.email());

        if (bcrypt.matches(uld.password(), utente.getPassword())) {
            return jwtTools.createToken(utente);
        } else {
            throw new UnauthorizedException("Credenziali errate");
        }
    }
}