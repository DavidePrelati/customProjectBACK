package davide.prelati.customProjectBACK.controllers;

import davide.prelati.customProjectBACK.exceptions.BadRequestException;
import davide.prelati.customProjectBACK.payloads.UserLoginDTO;
import davide.prelati.customProjectBACK.payloads.UserLoginResponseDTO;
import davide.prelati.customProjectBACK.payloads.UserRequiredDTO;
import davide.prelati.customProjectBACK.payloads.UserRequiredResponseDTO;
import davide.prelati.customProjectBACK.services.AuthService;
import davide.prelati.customProjectBACK.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginResponseDTO(authService.generateToken(body));
    }

    @PostMapping("/user/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRequiredResponseDTO registerUser(@RequestBody @Validated UserRequiredDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }

        return new UserRequiredResponseDTO(this.userService.saveUser(body).getId());
    }
}

