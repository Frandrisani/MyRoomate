package backend_myroommate.MyRoomate.service;

import backend_myroommate.MyRoomate.entities.User;
import backend_myroommate.MyRoomate.exceptions.NotFoundException;
import backend_myroommate.MyRoomate.payloads.NewUserDTO;

import backend_myroommate.MyRoomate.repository.UserDAO;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;


    public User findById(long id) {
        return this.userDAO.findById(id).orElseThrow(() -> new NotFoundException("User " + id + " has not been found"));
    }


    public User findByEmail(String email) {
        return this.userDAO.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User " + email + " has not been found"));
    }

    public User save(NewUserDTO payload) throws BadRequestException {
        User user = new User();
        user.setFirstName(payload.firstName());
        user.setLastName(payload.lastName());
        user.setPhoneNumber(payload.phoneNumber());
        user.setEmail(payload.email());
        user.setBirthdate(payload.birthdate());
        user.setGender(payload.gender());
        user.setUsage(payload.usage());
        user.setPassword(payload.password());
        return this.userDAO.save(user);
    }
}
