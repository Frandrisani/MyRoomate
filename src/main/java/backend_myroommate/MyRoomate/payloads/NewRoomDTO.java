package backend_myroommate.MyRoomate.payloads;
import backend_myroommate.MyRoomate.enums.TypeRoom;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewRoomDTO(
        @NotEmpty(message = "Title is mandatory")
        String title,

        @NotEmpty(message = "Description is mandatory")
        String description,

        @NotNull(message = "Price is mandatory")
        long price,

        @NotEmpty(message = "Address is mandatory")
        String address,

        @NotEmpty(message = "City is mandatory")
        String city,

        @NotEmpty(message = "Zip Code is mandatory")
        String zipCode,

        @NotNull(message = "Price is mandatory")
        int roommates,

        @NotNull(message = "Number of bathrooms is mandatory")
        int wc,


        TypeRoom type
) {}