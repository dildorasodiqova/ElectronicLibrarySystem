package uz.uzinfocom.electroniclibrarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.uzinfocom.electroniclibrarysystem.entity.Library;
@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    Library findByBookId(Long bookId);
}
