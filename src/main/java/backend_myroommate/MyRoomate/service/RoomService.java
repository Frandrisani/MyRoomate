package backend_myroommate.MyRoomate.service;
import backend_myroommate.MyRoomate.entities.Room;
import backend_myroommate.MyRoomate.entities.User;
import backend_myroommate.MyRoomate.exceptions.NotFoundException;
import backend_myroommate.MyRoomate.payloads.NewRoomDTO;
import backend_myroommate.MyRoomate.payloads.editRoomDTO;
import backend_myroommate.MyRoomate.repository.RoomDAO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomDAO roomDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private Cloudinary cloudinary;

    public Room findById(long id) {
        return roomDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Room with id " + id + " not found"));
    }

    public List<Room> findAll() {
        return this.roomDAO.findAll();
    }

    public List<Room> findAllByUserId(long userId) {
        return roomDAO.findAllByUserId(userId);
    }

    public List<Room> findAllByCity(String city){
        return roomDAO.findAllByCity(city);
    }

    public Room createRoom(long userId, NewRoomDTO body) throws IOException {
        User user = userService.findById(userId);
        Room room = new Room(body.title(), body.description(), body.price(), body.address(), body.city(), body.zipCode(), body.roommates(), body.wc(), body.type());
        room.setUser(user);
        return this.roomDAO.save(room);
    }

    public Room updateRoom(long roomId, editRoomDTO payload) {
        Room room = findById(roomId);
        room.setTitle(payload.title());
        room.setDescription(payload.description());
        room.setPrice(payload.price());
        room.setAddress(payload.address());
        room.setCity(payload.city());
        room.setZipCode(payload.zipCode());
        room.setRoommates(payload.roommates());
        room.setWc(payload.wc());
        room.setType(payload.type());
        return this.roomDAO.save(room);
    }

    public Room updateImage(long roomId, MultipartFile img ) throws IOException {
        Room found = this.findById(roomId);
        String url = (String) cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setImage(url);
        return this.roomDAO.save(found);
    }

    public void deleteRoom(long id) {
        Room room = findById(id);
        this.roomDAO.delete(room);
    }
}
