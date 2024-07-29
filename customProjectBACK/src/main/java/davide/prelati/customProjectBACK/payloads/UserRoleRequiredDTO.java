package davide.prelati.customProjectBACK.payloads;

import jakarta.validation.constraints.NotBlank;

public record UserRoleRequiredDTO(@NotBlank String name) {
}
