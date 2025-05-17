package uz.uzinfocom.electroniclibrarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.uzinfocom.electroniclibrarysystem.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameAndDeletedAtIsNull(String username);

    boolean existsByUsername(String username);

}
