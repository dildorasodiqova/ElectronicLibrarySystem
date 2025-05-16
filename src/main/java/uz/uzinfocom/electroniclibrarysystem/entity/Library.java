package uz.uzinfocom.electroniclibrarysystem.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Library extends BaseModel{
     Long bookId;
     String title;
     String author;
     Integer totalBooks = 0;
}
