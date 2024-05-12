package backend_myroommate.MyRoomate.payloads;

import jakarta.validation.constraints.NotEmpty;

public record editBioDTO(
        @NotEmpty(message = "Bio is mandatory")
        String bio
) {
}
