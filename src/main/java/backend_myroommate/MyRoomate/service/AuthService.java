package backend_myroommate.MyRoomate.service;


import backend_myroommate.MyRoomate.entities.User;
import backend_myroommate.MyRoomate.exceptions.BadRequestException;
import backend_myroommate.MyRoomate.mailgun.MailgunSender;
import backend_myroommate.MyRoomate.payloads.NewUserDTO;
import backend_myroommate.MyRoomate.repository.UserDAO;
import backend_myroommate.MyRoomate.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private MailgunSender mailgunSender;

    public User register(NewUserDTO payload) {
        if (this.userDAO.existsByEmail(payload.email()))
            throw new BadRequestException("Email " + payload.email() + " is already taken");
        User newUser = new User(payload.firstName(), payload.lastName(), payload.phoneNumber(), payload.email(), payload.birthdate(), payload.gender(), payload.usage(), encoder.encode(payload.password()));
        mailgunSender.sendRegistrationEmail(newUser);
        return this.userDAO.save(newUser);
    }

}
