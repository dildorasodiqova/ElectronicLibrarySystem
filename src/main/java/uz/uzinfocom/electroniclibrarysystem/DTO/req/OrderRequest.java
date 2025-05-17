package uz.uzinfocom.electroniclibrarysystem.DTO.req;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.uzinfocom.electroniclibrarysystem.entity.Order;
import uz.uzinfocom.electroniclibrarysystem.enums.OrderStatus;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    @NotNull(message = "Book ID is required")
    Long bookId;

    @NotNull(message = "User ID is required")
    Long userId;

    @NotNull(message = "Start date is required")
    LocalDate startDate;

    @NotNull(message = "End date is required")
    LocalDate endDate;

    public Order create() {
       Order order =  new Order();
       order.setStartDate(LocalDate.now());
       order.setEndDate(LocalDate.now());
       order.setStatus(OrderStatus.BOOKED);
       return order;
    }
}
