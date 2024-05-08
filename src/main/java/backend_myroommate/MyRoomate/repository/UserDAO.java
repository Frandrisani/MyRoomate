package backend_myroommate.MyRoomate.repository;

import backend_myroommate.MyRoomate.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
    User findByEmail (String email);

    boolean existsByEmail(String email);
}
