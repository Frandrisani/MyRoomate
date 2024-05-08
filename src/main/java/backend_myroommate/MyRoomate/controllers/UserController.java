package backend_myroommate.MyRoomate.controllers;

import backend_myroommate.MyRoomate.entities.User;
import backend_myroommate.MyRoomate.payloads.NewUserDTO;
import backend_myroommate.MyRoomate.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody NewUserDTO payload) throws BadRequestException {
        User user = userService.save(payload);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
