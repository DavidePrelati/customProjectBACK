package davide.prelati.customProjectBACK.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserRequiredDTO(
        @NotBlank(message = "Inserire un username.")
        @Size(min = 5, max = 15, message = "Inserire uno username compreso tra i 5 e i 15 caratteri")
        String username,
        @Email(message = "Inserisci una email valida.")
        String email,
        @NotBlank(message = "Inserire una password.")
        @Size(min = 8, message = "Inserire una password di almeno 8 caratteri")
        String password,
        @NotBlank(message = "Inserire un nome.")
        @Size(min = 3, max = 20, message = "Inserire un nome compreso tra i 3 e i 20 caratteri")
        String name,
        @NotEmpty(message = "Inserire un cognome.")
        @Size(min = 3, max = 20, message = "Inserire un cognome compreso tra i 3 e i 20 caratteri")
        String surname
) {
}
