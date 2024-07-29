package davide.prelati.customProjectBACK.payloads;

import jakarta.validation.constraints.NotBlank;

public record ShirtDTO(@NotBlank String name,
                       @NotBlank String size,
                       @NotBlank int number,
                       @NotBlank double price,
                       @NotBlank String urlImage,
                       @NotBlank String squad) {
}
