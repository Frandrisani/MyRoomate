package backend_myroommate.MyRoomate.payloads;
import backend_myroommate.MyRoomate.enums.TypeRoom;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewRoomDTO(
        @NotEmpty(message = "City is mandatory")
        String city,

        @NotEmpty(message = "Address is mandatory")
        String address,

        @NotNull(message = "Price is mandatory")
        long price,

        @NotEmpty(message = "Type is mandatory")
        TypeRoom type,

        @NotEmpty(message = "Size is mandatory")
        String size,

        @NotEmpty(message = "Number of rooms is mandatory")
        String rooms,

        @NotEmpty(message = "Number of bathrooms is mandatory")
        String wc,

        @NotEmpty(message = "Description is mandatory")
        String description,

        @NotEmpty(message = "Image URL is mandatory")
        String image
) {}