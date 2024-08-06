package davide.prelati.customProjectBACK.payloads;

import jakarta.validation.constraints.NotBlank;

public record NationDTO(@NotBlank String name, @NotBlank String url) {
}
