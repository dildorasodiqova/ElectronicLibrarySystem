package uz.uzinfocom.electroniclibrarysystem.service.orderservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.OrderRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.OrderResponse;
import uz.uzinfocom.electroniclibrarysystem.entity.Order;
import uz.uzinfocom.electroniclibrarysystem.enums.OrderStatus;
import uz.uzinfocom.electroniclibrarysystem.exception.ExceptionWithStatusCode;
import uz.uzinfocom.electroniclibrarysystem.repository.OrderRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    @Override
    public ResponseEntity<OrderResponse> makeOrder(OrderRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<List<OrderResponse>> viewAllOrders() {
        return null;
    }

    @Override
    public ResponseEntity<OrderResponse> acceptOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ExceptionWithStatusCode(404, "Order not found"));

        if (order.getStatus() != OrderStatus.ACCEPTED) {
            throw new ExceptionWithStatusCode(400, "Only accepted orders can be returned");
        }

        order.setStatus(OrderStatus.RETURNED);
        order.setReturnedDate(LocalDate.now());

        if (order.getReturnedDate().isAfter(order.getEndDate())) {
            long overdueDays = ChronoUnit.DAYS.between(order.getEndDate(), order.getReturnedDate());
            int pricePerDay = order.getBook().getPricePerDay();  // 1000 so‘m
            double penaltyPerDay = pricePerDay * 0.01;           // 1% = 10 so‘m
            double totalPenalty = penaltyPerDay * overdueDays;
            order.setFineAmount(totalPenalty);
        } else {
            order.setFineAmount(0D);
        }

        orderRepository.save(order);
        return orderMapper.toResponse(order);
    }
}
