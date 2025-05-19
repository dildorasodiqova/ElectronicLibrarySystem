package uz.uzinfocom.electroniclibrarysystem.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.BookRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.BookResponse;
import uz.uzinfocom.electroniclibrarysystem.service.bookservice.BookService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PostMapping
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookRequest request) {
        return bookService.addBook(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        return bookService.updateBook(id, request);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping("/getById/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable Long id){
        return bookService.getById(id);
    }
    @PermitAll
    @GetMapping("/getAll")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return bookService.getAllBooks();
    }
}
