package backend_myroommate.MyRoomate.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserLoginResponseDTO(String accessToken) {
}
