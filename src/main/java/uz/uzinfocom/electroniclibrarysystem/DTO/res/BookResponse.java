package uz.uzinfocom.electroniclibrarysystem.DTO.res;

import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.uzinfocom.electroniclibrarysystem.entity.Book;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponse {
    Long id;
    String title;
    String author;
    Integer pricePerDay;
    Boolean isDeleted;
    Boolean isBron;

    public BookResponse convert(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setPricePerDay(book.getPricePerDay());
        response.setIsDeleted(book.getIsDeleted());
        response.setIsBron(book.getIsBron());
        return response;
    }
}
