package uz.uzinfocom.electroniclibrarysystem.service.bookservice;

import org.springframework.http.ResponseEntity;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.BookRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.BookResponse;
import uz.uzinfocom.electroniclibrarysystem.entity.Book;

import java.util.List;

public interface BookService {
    ResponseEntity<BookResponse> addBook(BookRequest request);

    ResponseEntity<BookResponse> updateBook(Long id, BookRequest request);

    ResponseEntity<BookResponse> deleteBook(Long id);

    ResponseEntity<List<BookResponse>> getAllBooks();

    Book findById(Long bookId);

    void updateStatus(Long id, boolean b);
}
