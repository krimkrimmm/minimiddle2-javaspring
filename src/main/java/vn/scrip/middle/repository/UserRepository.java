package vn.scrip.middle.repository;
import vn.scrip.middle.entity.User;
import vn.scrip.middle.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole(UserRole userRole);
    Optional<User> findByEmail(String email);
}








