package uz.uzinfocom.electroniclibrarysystem.service.bookservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.BookRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.BookResponse;
import uz.uzinfocom.electroniclibrarysystem.entity.Book;
import uz.uzinfocom.electroniclibrarysystem.exception.ExceptionWithStatusCode;
import uz.uzinfocom.electroniclibrarysystem.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    @Override
    public ResponseEntity<BookResponse> addBook(BookRequest request) {
        List<BookResponse> byTitleAndAuthor = bookRepository.findByTitleAndAuthorAndIsDeletedTrue(request.getTitle(), request.getAuthor());
        if (!byTitleAndAuthor.isEmpty()) {
            throw new ExceptionWithStatusCode(400,"This book is already exists");
        }
        Book book=  bookRepository.save(request.create());
        return ResponseEntity.ok(new BookResponse().convert(book));
    }

    @Override
    public ResponseEntity<BookResponse> updateBook(Long id, BookRequest request) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ExceptionWithStatusCode(404, "Book not found"));
        bookRepository.save(request.update(book));
        return ResponseEntity.ok(new BookResponse().convert(book));
    }


    @Override
    public ResponseEntity<BookResponse> deleteBook(Long id) {
        Book book = findById(id);
        book.setIsDeleted(true);
        bookRepository.save(book);
        return ResponseEntity.ok(new BookResponse().convert(book));
    }

    @Override
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<Book> all = bookRepository.findAllByIsDeletedTrue();
        List<BookResponse> collect = all.stream().map(book -> new BookResponse().convert(book)).toList();
        return ResponseEntity.ok(collect);
    }

    @Override
    public Book findById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(()-> new ExceptionWithStatusCode(404, "Book not found"));
    }

    @Override
    public ResponseEntity<BookResponse> getById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ExceptionWithStatusCode(404, "Book not found"));
        return ResponseEntity.ok(new BookResponse().convert(book));

    }

    @Override
    public void updateStatus(Long id, boolean b) {
        Book book = findById(id);
        book.setIsBron(b);
        bookRepository.save(book);
    }
}
