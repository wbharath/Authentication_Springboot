package bharad.projects.todo.repository;

import bharad.projects.todo.entity.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);


}
