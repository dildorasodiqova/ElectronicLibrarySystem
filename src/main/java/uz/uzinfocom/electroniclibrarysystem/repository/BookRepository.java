package uz.uzinfocom.electroniclibrarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.BookResponse;
import uz.uzinfocom.electroniclibrarysystem.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<BookResponse>findByTitleAndAuthorAndIsDeletedTrue(String title, String author);

    List<Book> findAllByIsDeletedTrue();

}
