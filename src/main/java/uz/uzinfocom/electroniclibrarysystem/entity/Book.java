package uz.uzinfocom.electroniclibrarysystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Book extends BaseModel {
    String title;
    String author;
    Integer pricePerDay; // 1000 so‘m kabi
    private int quantity; // mavjud soni
    @ManyToOne
    private Library library;

}
