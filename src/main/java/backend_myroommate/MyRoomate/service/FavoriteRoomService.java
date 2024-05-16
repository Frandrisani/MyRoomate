package backend_myroommate.MyRoomate.service;

import backend_myroommate.MyRoomate.entities.FavoriteRoom;
import backend_myroommate.MyRoomate.entities.Room;
import backend_myroommate.MyRoomate.entities.User;
import backend_myroommate.MyRoomate.repository.FavoriteRoomDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteRoomService {

    @Autowired
    private FavoriteRoomDAO favoriteRoomDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    // Aggiungi una stanza ai preferiti dell'utente
    public void addRoomToUserFavorites(long userId, long roomId) {
        User user = userService.findById(userId);
        Room room = roomService.findById(roomId);
        FavoriteRoom favoriteRoom = new FavoriteRoom();
        favoriteRoom.setUser(user);
        favoriteRoom.setRoom(room);
        this.favoriteRoomDAO.save(favoriteRoom);
    }

    // Rimuovi una stanza dai preferiti dell'utente
    public void removeRoomFromUserFavorites(long userId, long roomId) {
        FavoriteRoom favoriteRoom = this.favoriteRoomDAO.findByUserIdAndRoomId(userId, roomId);
        if (favoriteRoom != null) {
            this.favoriteRoomDAO.delete(favoriteRoom);
        }
    }

    // Ritorna la lista delle stanze salvate nei preferiti dall'utente
    public List<Room> getUserFavoriteRooms(long userId) {
        List<FavoriteRoom> favoriteRooms = this.favoriteRoomDAO.findByUserId(userId);
        List<Room> rooms = new ArrayList<>();
        for (FavoriteRoom favoriteRoom : favoriteRooms) {
            rooms.add(favoriteRoom.getRoom());
        }
        return rooms;
    }
}
