package davide.prelati.customProjectBACK.controllers;

import davide.prelati.customProjectBACK.entities.User;
import davide.prelati.customProjectBACK.exceptions.BadRequestException;
import davide.prelati.customProjectBACK.payloads.UserLoginDTO;
import davide.prelati.customProjectBACK.payloads.UserLoginResponseDTO;
import davide.prelati.customProjectBACK.payloads.UserRequiredDTO;
import davide.prelati.customProjectBACK.services.AuthService;
import davide.prelati.customProjectBACK.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService utenteService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody @Validated UserLoginDTO utenteLoginDTO, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new BadRequestException(validationResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", ")));
        }
        return new UserLoginResponseDTO(authService.authenticateUserAndGenerateToken(utenteLoginDTO));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody @Validated UserRequiredDTO nuovoUtenteDTO, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new BadRequestException(validationResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", ")));
        }
        return utenteService.saveUser(nuovoUtenteDTO);
    }
}