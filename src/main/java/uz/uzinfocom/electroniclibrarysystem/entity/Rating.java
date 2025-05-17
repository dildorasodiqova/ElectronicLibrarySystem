package uz.uzinfocom.electroniclibrarysystem.entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Rating extends BaseModel{

    @ManyToOne
    UserEntity userEntity;

    @ManyToOne
    Book book;

    int stars; // 0–5
}
