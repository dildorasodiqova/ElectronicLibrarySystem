package uz.uzinfocom.electroniclibrarysystem.DTO.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.uzinfocom.electroniclibrarysystem.entity.Book;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequest {
    @NotBlank(message = "Title must not be empty")  // null va bo'sh satrga ruxsat yo'q
    String title;

    @NotBlank(message = "Author must not be empty") // null va bo'sh satrga ruxsat yo'q
    String author;

    @NotNull(message = "Price per day is required")
    @Min(value = 1, message = "Price per day must be at least 1") // minimal qiymat 1 dan kam bo'lmasin
    Integer pricePerDay;


    public Book create() {
        Book book = new Book();
        book.setTitle(getTitle());
        book.setAuthor(getAuthor());
        book.setPricePerDay(getPricePerDay());
        book.setCreatedAt(LocalDateTime.now());
        return book;
    }

    public Book update(Book book) {
        book.setTitle(getTitle());
        book.setAuthor(getAuthor());
        book.setUpdatedAt(LocalDateTime.now());
        book.setPricePerDay(getPricePerDay());
        return book;
    }



}
