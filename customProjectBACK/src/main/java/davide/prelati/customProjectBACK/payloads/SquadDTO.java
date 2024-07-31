package davide.prelati.customProjectBACK.payloads;

import davide.prelati.customProjectBACK.enums.Sponsor;
import jakarta.validation.constraints.NotBlank;

public record SquadDTO(@NotBlank String name,
                       @NotBlank Sponsor sponsor) {
}
