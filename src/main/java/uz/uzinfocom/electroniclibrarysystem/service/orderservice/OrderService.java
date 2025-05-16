package uz.uzinfocom.electroniclibrarysystem.service.orderservice;

import org.springframework.http.ResponseEntity;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.OrderRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.OrderResponse;

import java.util.List;

public interface OrderService {
    ResponseEntity<OrderResponse> makeOrder(OrderRequest request);

    ResponseEntity<List<OrderResponse>> viewAllOrders();

    ResponseEntity<OrderResponse> acceptOrder(Long id);
}
