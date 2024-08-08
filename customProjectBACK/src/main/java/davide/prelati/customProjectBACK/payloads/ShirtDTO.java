package davide.prelati.customProjectBACK.payloads;

import davide.prelati.customProjectBACK.entities.Squad;
import jakarta.validation.constraints.NotBlank;

public record ShirtDTO(@NotBlank String name,

                       @NotBlank int number,
                       @NotBlank double price,
                       @NotBlank String urlImage,
                       @NotBlank Squad squad) {
}
