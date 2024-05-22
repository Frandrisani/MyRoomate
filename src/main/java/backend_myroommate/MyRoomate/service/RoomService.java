package backend_myroommate.MyRoomate.service;
import backend_myroommate.MyRoomate.entities.Room;
import backend_myroommate.MyRoomate.entities.User;
import backend_myroommate.MyRoomate.exceptions.NotFoundException;
import backend_myroommate.MyRoomate.mailgun.MailgunSender;
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

    @Autowired
    private MailgunSender mailgunSender;

    // CERCA ANNUNCI CON IL SUO ID
    public Room findById(long id) {
        return roomDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Room with id " + id + " not found"));
    }

    // TUTTI GLI ANNUNCI
    public List<Room> findAll() {
        return this.roomDAO.findAll();
    }

    // GLI ANNUNCI IN BASE ALL'ID DELL'USER CHE GLI HA PUBBLICATI
    public List<Room> findAllByUserId(long userId) {
        return roomDAO.findAllByUserId(userId);
    }

    // GLI ANNUNCI IN BASE ALLA CITTA' DOVE RISIEDE LA STANZA
    public List<Room> findAllByCity(String city){
        return roomDAO.findAllByCity(city);
    }

    // CREA UN NUOVO ANNUNCIO
    public Room createRoom(long userId, NewRoomDTO body) throws IOException {
        User user = userService.findById(userId);
        Room room = new Room(body.title(), body.description(), body.price(), body.address(), body.city(), body.zipCode(), body.bedrooms(), body.wc(), body.type());
        room.setUser(user);
        return this.roomDAO.save(room);
    }

    // MODIFICA ANNUNCIO
    public Room updateRoom(long roomId, editRoomDTO payload) {
        Room room = findById(roomId);
        room.setTitle(payload.title());
        room.setDescription(payload.description());
        room.setPrice(payload.price());
        room.setAddress(payload.address());
        room.setCity(payload.city());
        room.setZipCode(payload.zipCode());
        room.setBedrooms(payload.bedrooms());
        room.setWc(payload.wc());
        room.setType(payload.type());
        return this.roomDAO.save(room);
    }

    // AGGIUNGI O MODIFICA IMMAGINE
    public Room updateImage(long roomId, MultipartFile img ) throws IOException {
        Room found = this.findById(roomId);
        String url = (String) cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setImage(url);
        return this.roomDAO.save(found);
    }

    // CANCELLA ANNUNCIO
    public void deleteRoom(long id) {
        Room room = findById(id);
        this.roomDAO.delete(room);
    }

}
