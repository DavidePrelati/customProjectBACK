package davide.prelati.customProjectBACK.repositories;

import davide.prelati.customProjectBACK.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByName(String name);
}
