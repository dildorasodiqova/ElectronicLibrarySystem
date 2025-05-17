package uz.uzinfocom.electroniclibrarysystem.DTO.res;

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
public class OrderResponse {
    Long id;
    String bookTitle;
    String userFullName;
    LocalDate startDate;
    LocalDate endDate;
    LocalDate returnedDate;
    OrderStatus status;
    Double fineAmount;

    public OrderResponse convert(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setBookTitle(order.getBook().getTitle());
        orderResponse.setUserFullName(order.getUser().getFullName());
        orderResponse.setStartDate(order.getStartDate());
        orderResponse.setEndDate(order.getEndDate());
        orderResponse.setReturnedDate(order.getReturnedDate());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setFineAmount(order.getFineAmount());
        return orderResponse;
    }
}
