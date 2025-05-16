package uz.uzinfocom.electroniclibrarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.uzinfocom.electroniclibrarysystem.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
