package uz.uzinfocom.electroniclibrarysystem.service.orderservice;

import org.springframework.http.ResponseEntity;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.OrderRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.OrderResponse;
import uz.uzinfocom.electroniclibrarysystem.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    ResponseEntity<OrderResponse> makeOrder(OrderRequest request);

    ResponseEntity<List<OrderResponse>> viewAllOrders(Long userId);

    ResponseEntity<OrderResponse> acceptOrder(Long id);
    ResponseEntity<OrderResponse> returnOrder(Long id);
    boolean existsByUserIdAndBookIdAndStatusIn(Long userId, Long bookId, List<OrderStatus> statuses);

}
