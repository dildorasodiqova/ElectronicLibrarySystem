package uz.uzinfocom.electroniclibrarysystem.entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.uzinfocom.electroniclibrarysystem.enums.OrderStatus;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Order extends BaseModel {
    @ManyToOne
    UserEntity userEntity;

    @ManyToOne
    Book book;

    LocalDate startDate;
    LocalDate endDate;
    LocalDate returnedDate;

    OrderStatus status;

    Double fineAmount = 0D;
}
