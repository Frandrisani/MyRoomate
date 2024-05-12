package backend_myroommate.MyRoomate.payloads;

import jakarta.validation.constraints.NotEmpty;

public record editHobbiesDTO(
        @NotEmpty(message = "Hobbies is mandatory")
        String hobbies
) {
}
