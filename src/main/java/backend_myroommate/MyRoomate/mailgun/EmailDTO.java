package backend_myroommate.MyRoomate.mailgun;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record EmailDTO(
                       @NotEmpty(message = "L'email è obbligatoria")
                       @Email(message = "Inserisci una email valida")
                       String email,
                       @NotEmpty (message = "L'oggetto' è obbligatorio")
                       String subject,
                       @NotEmpty (message = "Il testo è obbligatorio")
                       String text) {

}
