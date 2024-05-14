package backend_myroommate.MyRoomate.mailgun;


import backend_myroommate.MyRoomate.entities.User;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailgunSender {
    private String apiKey;
    private String domainName;

    @Value("${mailgun.email}")
    private String email;

    public MailgunSender(@Value("${mailgun.apikey}") String apiKey, @Value("${mailgun.domainname}") String domainName) {
        this.apiKey = apiKey;
        this.domainName = domainName;

    }
    public void sendRegistrationEmail (User user) {
        try {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/"+ this.domainName + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", this.email)
                .queryString("to", user.getEmail())
                .queryString("subject", "MyRoommate: registrazione completata. ")
                .queryString("text", "Benvenuta/o a bordo" + user.getFirstName() + " e grazie per esserti registrato! Ora fai subito il login e inizia ad esplorare le offerte o inserisci un annuncio")
                .asJson();
        System.out.println(response.getBody());
        } catch (Exception e) {
            System.out.println("Si è verificato un errore durante l'invio dell'email: " + e.getMessage());
        }
    }


}
