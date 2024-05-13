package backend_myroommate.MyRoomate.payloads;

import jakarta.validation.constraints.NotEmpty;

public record editAvatarDTO(
        @NotEmpty(message = "Immage is mandatory")
        String imageUrl
) {
}
