package uz.uzinfocom.electroniclibrarysystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.uzinfocom.electroniclibrarysystem.enums.OrderStatus;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Order extends BaseModel{
    @ManyToOne
    User user;

    @ManyToOne
    Book book;

    LocalDate startDate;
    LocalDate endDate;
    LocalDate returnedDate;

    OrderStatus status;

    Double fineAmount = 0;
}
