package backend_myroommate.MyRoomate.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotEmpty(message = "First name is mandatory")
        @Size(min = 2, message = "First name must be longer than 2 chars")
        String firstName,

        @NotEmpty(message = "Last name is mandatory")
        @Size(min = 2, message = "Last name must be longer than 2 chars")
        String lastName,

        @NotEmpty(message = "Phone number is mandatory")
        String phoneNumber,

        @NotEmpty(message = "Email is mandatory")
        @Email(message = "Please provide a valid email address")
        String email,

        @NotEmpty(message = "Birthdate is mandatory")
        String birthdate,

        String gender,
        @NotEmpty(message = "Birthdate is mandatory")
        String cityOfBirth,
        @NotEmpty(message = "Birthdate is mandatory")
        String countryOfBirth,

        String usage,

        @NotEmpty(message = "Password is mandatory")
        @Size(min = 6, message = "Password must be longer than 6 chars")
        String password
) {}
