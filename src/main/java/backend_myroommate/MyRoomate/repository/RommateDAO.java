package backend_myroommate.MyRoomate.repository;
import backend_myroommate.MyRoomate.entities.Room;
import backend_myroommate.MyRoomate.entities.Roommate;
import backend_myroommate.MyRoomate.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RommateDAO extends JpaRepository<Roommate, Long> {
    List<Roommate> findByCoinquilinoAndRoomcoinquilino(User coinquilino, Room roomcoinquilino);
    List<Roommate> findByRoomcoinquilino(Room roomcoinquilino);
}
