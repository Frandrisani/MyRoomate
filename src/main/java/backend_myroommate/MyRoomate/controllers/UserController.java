package backend_myroommate.MyRoomate.controllers;

import backend_myroommate.MyRoomate.entities.User;
import backend_myroommate.MyRoomate.exceptions.UnauthorizedException;
import backend_myroommate.MyRoomate.payloads.*;
import backend_myroommate.MyRoomate.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public User findMe(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }

    @PutMapping("/{userId}/bio")
    public ResponseEntity<User> editBio(@PathVariable long userId, @RequestBody @Validated editBioDTO payload) {
        User updatedUser = userService.editBio(userId, payload);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/hobbies")
    public ResponseEntity<User> editHobbies(@PathVariable long userId, @RequestBody @Validated editHobbiesDTO payload) {
        User updatedUser = userService.editHobbies(userId, payload);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/preferences")
    public ResponseEntity<User> editPreference(@PathVariable long userId, @RequestBody @Validated editPreferenceDTO payload) {
        User updatedUser = userService.editPreference(userId, payload);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/info")
    public ResponseEntity<User> editInfo(@PathVariable long userId, @RequestBody @Validated editUserInfoDTO payload) {
        User updatedUser = userService.editInfo(userId, payload);
        return ResponseEntity.ok(updatedUser);
    }
    @PostMapping("/upload")
    public User uploadAvatar(@RequestParam("avatar") MultipartFile image,
                                @AuthenticationPrincipal User currentUser) throws IOException {
        return this.userService.uploadImage(image, currentUser.getId());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
