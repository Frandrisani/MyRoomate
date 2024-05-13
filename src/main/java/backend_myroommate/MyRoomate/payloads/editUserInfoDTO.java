package backend_myroommate.MyRoomate.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record editUserInfoDTO(
        @NotEmpty(message = "Preferences is mandatory")
        @Email
        String email,
        @NotEmpty(message = "Preferences is mandatory")
        String phoneNumber
) {
}
