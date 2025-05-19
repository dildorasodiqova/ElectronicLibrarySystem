package uz.uzinfocom.electroniclibrarysystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.OrderRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.OrderResponse;
import uz.uzinfocom.electroniclibrarysystem.enums.OrderStatus;
import uz.uzinfocom.electroniclibrarysystem.service.orderservice.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<OrderResponse> makeOrder(@Valid @RequestBody OrderRequest request) {
        return orderService.makeOrder(request);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @GetMapping("/userId")
    public ResponseEntity<List<OrderResponse>> viewOrders(@PathVariable Long userId) {
        return orderService.viewAllOrders(userId);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PostMapping("/{id}/accept")
    public ResponseEntity<OrderResponse> acceptOrder(@PathVariable Long id) {
        return orderService.acceptOrder(id);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @GetMapping("/return-order")
    public ResponseEntity<OrderResponse> returnOrder(Long id){
        return orderService.returnOrder(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @GetMapping("/get-all")
    public ResponseEntity<List<OrderResponse>> getAll(@RequestParam(required = false) OrderStatus orderStatus){
        return orderService.getAll(orderStatus);
    }



}
