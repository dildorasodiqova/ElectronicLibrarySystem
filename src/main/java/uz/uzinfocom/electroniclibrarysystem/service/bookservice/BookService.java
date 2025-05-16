package uz.uzinfocom.electroniclibrarysystem.service.bookservice;

import org.springframework.http.ResponseEntity;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.BookRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.BookResponse;

import java.util.List;

public interface BookService {
    ResponseEntity<BookResponse> addBook(BookRequest request);

    ResponseEntity<BookResponse> updateBook(Long id, BookRequest request);

    ResponseEntity<BookResponse> deleteBook(Long id);

    List<BookResponse> getAllBooks();
}
