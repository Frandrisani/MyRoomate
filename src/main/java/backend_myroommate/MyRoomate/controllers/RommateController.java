package backend_myroommate.MyRoomate.controllers;

import backend_myroommate.MyRoomate.entities.User;
import backend_myroommate.MyRoomate.service.RommateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roommates")
public class RommateController {

    @Autowired
    private RommateService rommateService;

    @PostMapping("/add")
    public ResponseEntity<String> addRommateToRoom(@RequestParam String userEmail, @RequestParam long roomId) {
        try {
            rommateService.addRommateToRoom(userEmail, roomId);
            return ResponseEntity.ok("Roommate added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    public void removeRommateFromRoom(@RequestParam String userEmail, @RequestParam long roomId) {
        rommateService.removeRommateFromRoom(userEmail, roomId);
    }

    @GetMapping("/room/{roomId}")
    public List<User> getRoommatesByRoom(@PathVariable long roomId) {
        return rommateService.getRoommatesByRoom(roomId);
    }

}