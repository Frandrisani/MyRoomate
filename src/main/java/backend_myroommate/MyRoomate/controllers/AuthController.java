package backend_myroommate.MyRoomate.controllers;


import backend_myroommate.MyRoomate.entities.User;
import backend_myroommate.MyRoomate.exceptions.BadRequestException;
import backend_myroommate.MyRoomate.payloads.NewUserDTO;
import backend_myroommate.MyRoomate.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody @Validated NewUserDTO payload,
                         BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.authService.register(payload);
    }
}
