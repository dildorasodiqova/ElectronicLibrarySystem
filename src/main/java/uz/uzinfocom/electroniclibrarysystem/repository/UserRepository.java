package uz.uzinfocom.electroniclibrarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.uzinfocom.electroniclibrarysystem.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndDeletedAtIsNull(String username);

    boolean findByUsername(String username);
}
