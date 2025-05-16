package uz.uzinfocom.electroniclibrarysystem.DTO.req;

import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.uzinfocom.electroniclibrarysystem.entity.Book;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequest {
    String title;
    String author;
    Integer quantity; // nechta qborligi
    Integer pricePerDay; // bir kunlik to'lovi qancha

    public Book create() {
        Book book = new Book();
        book.setTitle(getTitle());
        book.setAuthor(getAuthor());
        book.setQuantity(getQuantity());
        book.setPricePerDay(getPricePerDay());
        return book;
    }

    public Book update(Book book) {
        book.setTitle(getTitle());
        book.setAuthor(getAuthor());
        book.setQuantity(getQuantity());
        book.setPricePerDay(getPricePerDay());
        return book;
    }
}
