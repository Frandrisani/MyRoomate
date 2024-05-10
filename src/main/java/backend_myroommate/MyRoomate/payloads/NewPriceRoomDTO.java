package backend_myroommate.MyRoomate.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewPriceRoomDTO(
        @NotEmpty(message = "Price is mandatory")
        @Size(min = 2, message = "Price must be longer than 2 chars")
        long price
) {
}