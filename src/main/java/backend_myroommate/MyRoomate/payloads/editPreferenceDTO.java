package backend_myroommate.MyRoomate.payloads;

import jakarta.validation.constraints.NotEmpty;

public record editPreferenceDTO(
        @NotEmpty(message = "Preferences is mandatory")
        String preferences
) {
}
