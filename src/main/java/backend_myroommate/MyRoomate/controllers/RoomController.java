package backend_myroommate.MyRoomate.controllers;

import backend_myroommate.MyRoomate.entities.Room;
import backend_myroommate.MyRoomate.payloads.NewRoomDTO;
import backend_myroommate.MyRoomate.payloads.editRoomDTO;
import backend_myroommate.MyRoomate.service.RoomService;
import backend_myroommate.MyRoomate.service.UserService;
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

    // Metodo che ottiene tutte le stanze
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.findAll();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    // Metodo che ottiene le stanze per città
    @GetMapping(params = "city")
    public ResponseEntity<List<Room>> getRoomsByCity(@RequestParam String city) {
        List<Room> rooms = roomService.findAllByCity(city);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Room>> getRoomsByUserId(@PathVariable long userId) {
        List<Room> rooms = roomService.findAllByUserId(userId);
        return ResponseEntity.ok(rooms);
    }

    @PostMapping("/create/{userIdRoom}")
    public ResponseEntity<Room> createRoom(@PathVariable long userIdRoom,
                                           @Validated @RequestBody NewRoomDTO newRoomDTO) throws IOException {

        long userId = userService.findById(userIdRoom).getId();
        Room createdRoom = roomService.createRoom( userId, newRoomDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<Room> updateRoom(@PathVariable long roomId,
                                           @RequestBody editRoomDTO editRoomDTO) {
        Room updatedRoom = roomService.updateRoom(roomId, editRoomDTO);
        return ResponseEntity.ok(updatedRoom);
    }

    @PutMapping("/{roomId}/image")
    public ResponseEntity<Room> updateRoomImage(@PathVariable long roomId,
                                                @RequestParam("image") MultipartFile image) throws IOException {
        Room updatedRoom = roomService.updateImage(roomId, image);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable long roomId) {
        roomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

}