package backend_myroommate.MyRoomate.mailgun;


import backend_myroommate.MyRoomate.entities.Room;
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
                .queryString("text", "Benvenuta/o a bordo " + user.getFirstName() + " e grazie per esserti registrato! Ora fai subito il login e inizia ad esplorare le offerte o inserisci un annuncio")
                .asJson();
        System.out.println(response.getBody());
        } catch (Exception e) {
            System.out.println("Si è verificato un errore durante l'invio dell'email: " + e.getMessage());
        }
    }

    public void sendInfoEmail (Room room, User user,  String text) {
        try {
            HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/"+ this.domainName + "/messages")
                    .basicAuth("api", this.apiKey)
                    .queryString("from", this.email)
                    .queryString("to", room.getUser().getEmail())
                    .queryString("subject", "MyRoommate - there is a request for the announcement: " + room.getTitle() + " ")
                    .queryString("text", "Request text: \n" + text + " \n \nContacted by: \n" + user.getFirstName() + " " + user.getLastName() + " \nEmail: " + user.getEmail() + " \nPhone number: " + user.getPhoneNumber() + " \n \nUse the contact information to write an email, call or send a message. You are expected to respond to the request within 24 hours. If you have any problems with your request, you can write to us at support@myroommate.it")
                    .asJson();
            System.out.println(response.getBody());
        } catch (Exception e) {
            System.out.println("Si è verificato un errore durante l'invio dell'email: " + e.getMessage());
        }
    }


}
