package uz.uzinfocom.electroniclibrarysystem.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Book extends BaseModel {
    String title;
    String author;
    Integer pricePerDay; // 1000 so‘m kabi
    Boolean isBron =  false;


}
