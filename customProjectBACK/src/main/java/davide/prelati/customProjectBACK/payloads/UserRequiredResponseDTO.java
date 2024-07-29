package davide.prelati.customProjectBACK.payloads;

import jakarta.validation.constraints.NotNull;

public record UserRequiredResponseDTO(@NotNull long id) {
}
