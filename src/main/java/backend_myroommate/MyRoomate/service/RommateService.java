package backend_myroommate.MyRoomate.service;

import backend_myroommate.MyRoomate.entities.FavoriteRoom;
import backend_myroommate.MyRoomate.entities.Room;
import backend_myroommate.MyRoomate.entities.Roommate;
import backend_myroommate.MyRoomate.entities.User;
import backend_myroommate.MyRoomate.repository.RommateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RommateService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RommateDAO roommateDAO;

    public void addRommateToRoom(String userEmail, long roomId) {
        User user = userService.findByEmail(userEmail);
        Room room = roomService.findById(roomId);

        List<Roommate> existingRoommates = roommateDAO.findByCoinquilinoAndRoomcoinquilino(user, room);
        if (!existingRoommates.isEmpty()) {
            throw new IllegalArgumentException("User is already associated with this room");
        }

        Roommate roommate = new Roommate();
        roommate.setCoinquilino(user);
        roommate.setRoomcoinquilino(room);
        roommateDAO.save(roommate);
    }

    public void removeRommateFromRoom(String userEmail, long roomId) {
        User user = userService.findByEmail(userEmail);
        Room room = roomService.findById(roomId);
        List<Roommate> roommates = roommateDAO.findByCoinquilinoAndRoomcoinquilino(user, room);
        if (!roommates.isEmpty()) {
            roommateDAO.delete(roommates.get(0));
        }
    }

    public List<User> getRoommatesByRoom(long roomId) {
        Room room = roomService.findById(roomId);
        List<Roommate> roommates = roommateDAO.findByRoomcoinquilino(room);
        List<User> users = new ArrayList<>();
        for (Roommate roommate : roommates) {
            users.add(roommate.getCoinquilino());
        }
        return users;
    }

}
