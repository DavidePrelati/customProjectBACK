package davide.prelati.customProjectBACK.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerDTO(@NotBlank String name,
                          @NotBlank String surname,
                          @NotBlank String username,
                          @NotBlank @Email String email) {
}
