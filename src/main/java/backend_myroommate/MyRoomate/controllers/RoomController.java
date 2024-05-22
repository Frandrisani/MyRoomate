package backend_myroommate.MyRoomate.controllers;

import backend_myroommate.MyRoomate.entities.Room;
import backend_myroommate.MyRoomate.entities.User;
import backend_myroommate.MyRoomate.mailgun.MailgunSender;
import backend_myroommate.MyRoomate.payloads.NewRoomDTO;
import backend_myroommate.MyRoomate.payloads.editRoomDTO;
import backend_myroommate.MyRoomate.service.RoomService;
import backend_myroommate.MyRoomate.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailgunSender mailgunSender;

    // TUTTI GLI ANNUNCI
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.findAll();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    // UN PRECISO ANNUNCIO
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable long id) {
        Room room = roomService.findById(id);
        return ResponseEntity.ok(room);
    }

    // GLI ANNUNCI IN BASE ALLA CITTA' DOVE RISIEDE LA STANZA
    @GetMapping(params = "city")
    public ResponseEntity<List<Room>> getRoomsByCity(@RequestParam String city) {
        List<Room> rooms = roomService.findAllByCity(city);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    // GLI ANNUNCI IN BASE ALL'ID DELL'USER CHE GLI HA PUBBLICATI
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Room>> getRoomsByUserId(@PathVariable long userId) {
        List<Room> rooms = roomService.findAllByUserId(userId);
        return ResponseEntity.ok(rooms);
    }

    // CREA UN NUOVO ANNUNCIO
    @PostMapping("/create/{userIdRoom}")
    public ResponseEntity<Room> createRoom(@PathVariable long userIdRoom,
                                           @Validated @RequestBody NewRoomDTO newRoomDTO) throws IOException {

        long userId = userService.findById(userIdRoom).getId();
        Room createdRoom = roomService.createRoom( userId, newRoomDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
    }

    // MODIFICA ANNUNCIO
    @PutMapping("/{roomId}")
    public ResponseEntity<Room> updateRoom(@PathVariable long roomId,
                                           @RequestBody editRoomDTO editRoomDTO) {
        Room updatedRoom = roomService.updateRoom(roomId, editRoomDTO);
        return ResponseEntity.ok(updatedRoom);
    }

    // AGGIUNGI O MODIFICA IMMAGINE
    @PutMapping("/{roomId}/images")
    public ResponseEntity<Room> updateRoomImages(@PathVariable long roomId,
                                                 @RequestParam("images") List<MultipartFile> images) throws IOException {
        Room room = roomService.findById(roomId);
        for (MultipartFile image : images) {
            room = roomService.updateImage(roomId, image);
        }
        return ResponseEntity.ok(room);
    }

    // CANCELLA ANNUNCIO
    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable long roomId) {
        roomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/send-info-email")
    public void sendInfoEmail(@RequestBody InfoEmailRequest infoEmailRequest) {
        Room found = roomService.findById(infoEmailRequest.getRoomId());
        User foundUser = userService.findById(infoEmailRequest.getUserId());
        mailgunSender.sendInfoEmail(found, foundUser, infoEmailRequest.getText());
    }

    @Getter
    @Setter
    public static class InfoEmailRequest {
        private Long roomId;
        private Long userId;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String text;

        // getter e setter
    }

}
