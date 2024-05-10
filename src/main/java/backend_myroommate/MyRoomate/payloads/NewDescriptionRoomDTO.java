package backend_myroommate.MyRoomate.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewDescriptionRoomDTO(
        @NotEmpty(message = "Description is mandatory")
        @Size(min = 2, message = "Description must be longer than 2 chars")
        String description
) {
}
