package uz.uzinfocom.electroniclibrarysystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.OrderRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.OrderResponse;
import uz.uzinfocom.electroniclibrarysystem.service.orderservice.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    // Foydalanuvchi buyurtma qiladi
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<OrderResponse> makeOrder(@Valid @RequestBody OrderRequest request) {
        return orderService.makeOrder(request);
    }

    // Operator buyurtmalarni ko'radi
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @GetMapping
    public ResponseEntity<List<OrderResponse>> viewOrders() {
        return orderService.viewAllOrders();
    }

    // Operator tasdiqlaydi
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PostMapping("/{id}/accept")
    public ResponseEntity<OrderResponse> acceptOrder(@PathVariable Long id) {
        return orderService.acceptOrder(id);
    }
}
