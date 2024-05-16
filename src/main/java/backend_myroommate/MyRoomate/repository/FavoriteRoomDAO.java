package backend_myroommate.MyRoomate.repository;

import backend_myroommate.MyRoomate.entities.FavoriteRoom;
import backend_myroommate.MyRoomate.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRoomDAO extends JpaRepository<FavoriteRoom, Long> {
    FavoriteRoom findByUserIdAndRoomId(Long userId, Long roomId);
    List<FavoriteRoom> findByUserId(Long userId);
}
