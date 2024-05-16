package backend_myroommate.MyRoomate.controllers;

import backend_myroommate.MyRoomate.entities.Room;
import backend_myroommate.MyRoomate.service.FavoriteRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite-rooms")
public class FavoriteRoomController {

    @Autowired
    private FavoriteRoomService favoriteRoomService;

    // Aggiungi una stanza ai preferiti dell'utente
    @PostMapping("/{userId}/{roomId}")
    public ResponseEntity<Void> addRoomToUserFavorites(@PathVariable long userId, @PathVariable long roomId) {
        favoriteRoomService.addRoomToUserFavorites(userId, roomId);
        return ResponseEntity.ok().build();
    }

    // Rimuovi una stanza dai preferiti dell'utente
    @DeleteMapping("/{userId}/{roomId}")
    public ResponseEntity<Void> removeRoomFromUserFavorites(@PathVariable long userId, @PathVariable long roomId) {
        favoriteRoomService.removeRoomFromUserFavorites(userId, roomId);
        return ResponseEntity.ok().build();
    }

    // Ritorna la lista delle stanze salvate nei preferiti dall'utente
    @GetMapping("/{userId}")
    public ResponseEntity<List<Room>> getUserFavoriteRooms(@PathVariable long userId) {
        List<Room> favoriteRooms = favoriteRoomService.getUserFavoriteRooms(userId);
        return ResponseEntity.ok(favoriteRooms);
    }
}
