package uz.uzinfocom.electroniclibrarysystem.service.bookservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.BookRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.BookResponse;
import uz.uzinfocom.electroniclibrarysystem.entity.Book;
import uz.uzinfocom.electroniclibrarysystem.entity.Library;
import uz.uzinfocom.electroniclibrarysystem.exception.ExceptionWithStatusCode;
import uz.uzinfocom.electroniclibrarysystem.repository.BookRepository;
import uz.uzinfocom.electroniclibrarysystem.service.libraryservice.LibraryService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final LibraryService libraryService;

    @Override
    public ResponseEntity<BookResponse> addBook(BookRequest request) {
        List<BookResponse> byTitleAndAuthor = bookRepository.findByTitleAndAuthor(request.getTitle(), request.getAuthor());
        if (byTitleAndAuthor.isEmpty()){
           Book book=  bookRepository.save(request.create());
            Library library =  new Library(book.getId(), book.getTitle(), book.getAuthor(), book.getQuantity());
            libraryService.save(library);
        }else {
            for (BookResponse bookResponse : byTitleAndAuthor) {
                Book book = bookRepository.findById(bookResponse.getId()).orElseThrow(()->new ExceptionWithStatusCode(404, "Book not found"));
                book.setQuantity(book.getQuantity() + request.getQuantity());
                bookRepository.save(book);
                libraryService.setBookById(book.getId(), book.getQuantity());
            }

        }
    }

    @Override
    public ResponseEntity<BookResponse> updateBook(Long id, BookRequest request) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ExceptionWithStatusCode(404, "Book not found"));
        bookRepository.save(request.update(book));
        return ResponseEntity.ok(mapToBookRes(book));
    }

    private BookResponse mapToBookRes(Book book) {
       return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getPricePerDay(), book.getQuantity());
    }

    @Override
    public ResponseEntity<BookResponse> deleteBook(Long id) {
        return null;
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return null;
    }
}
