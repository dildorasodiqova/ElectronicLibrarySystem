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
public class Rating extends BaseModel{

    @ManyToOne
    User user;

    @ManyToOne
    Book book;

    int stars; // 0–5
}
