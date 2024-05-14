package backend_myroommate.MyRoomate.repository;

import backend_myroommate.MyRoomate.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomDAO extends JpaRepository<Room, Long> {
    List<Room> findAllByUserId(long userId);
    List<Room> findAllByCity(String city);
}
