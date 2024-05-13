package backend_myroommate.MyRoomate.service;

import backend_myroommate.MyRoomate.entities.User;
import backend_myroommate.MyRoomate.exceptions.NotFoundException;
import backend_myroommate.MyRoomate.payloads.*;

import backend_myroommate.MyRoomate.repository.UserDAO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Cloudinary cloudinary;

    public User findById(long id) {
        return this.userDAO.findById(id).orElseThrow(() -> new NotFoundException("User " + id + " has not been found"));
    }


    public User findByEmail(String email) {
        return this.userDAO.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User " + email + " has not been found"));
    }

    public User editInfo(long userId, editUserInfoDTO payload) {
        User user = findById(userId);
        user.setEmail(payload.email());
        user.setPhoneNumber(payload.phoneNumber());
        return this.userDAO.save(user);
    }

    public User editBio(long userId, editBioDTO payload) {
        User user = findById(userId);
        user.setBio(payload.bio());
        return this.userDAO.save(user);
    }

    public User editHobbies(long userId, editHobbiesDTO payload) {
        User user = findById(userId);
        user.setHobby(payload.hobbies());
        return this.userDAO.save(user);
    }

    public User editPreference(long userId, editPreferenceDTO payload) {
        User user = findById(userId);
        user.setCohabitationPreferences(payload.preferences());
        return this.userDAO.save(user);
    }

    public User uploadImage(MultipartFile img, long userId) throws IOException {
        User found = this.findById(userId);
        String url = (String) cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(url);
        return this.userDAO.save(found);
    }

    public void deleteUser(long userId) {
        User user = findById(userId);
        this.userDAO.delete(user);
    }
}
