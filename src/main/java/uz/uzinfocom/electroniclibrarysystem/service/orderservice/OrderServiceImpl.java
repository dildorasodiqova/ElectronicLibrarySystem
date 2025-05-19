package uz.uzinfocom.electroniclibrarysystem.service.orderservice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.OrderRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.PaymentRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.OrderResponse;
import uz.uzinfocom.electroniclibrarysystem.entity.Book;
import uz.uzinfocom.electroniclibrarysystem.entity.Order;
import uz.uzinfocom.electroniclibrarysystem.enums.OrderStatus;
import uz.uzinfocom.electroniclibrarysystem.exception.ExceptionWithStatusCode;
import uz.uzinfocom.electroniclibrarysystem.repository.OrderRepository;
import uz.uzinfocom.electroniclibrarysystem.service.bookservice.BookService;
import uz.uzinfocom.electroniclibrarysystem.service.paymentService.PaymentService;
import uz.uzinfocom.electroniclibrarysystem.service.userservice.UserService;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final UserService userService;
    private final PaymentService paymentService;

    @Override
    public ResponseEntity<OrderResponse> makeOrder(OrderRequest request) {
        Order order = request.create();
        order.setUserEntity(userService.findById(request.getUserId()));

        Book book = bookService.findById(request.getBookId());
        if (book.getIsBron()) {
            throw new ExceptionWithStatusCode(400, "This book is already bron");
        }
        order.setBook(book);

        order.setFineAmount(Double.valueOf(book.getPricePerDay()));

        bookService.updateStatus(order.getBook().getId(), true);

        orderRepository.save(order);

        long overdueDays = ChronoUnit.DAYS.between(order.getEndDate(), order.getReturnedDate()); //nechi kunga olyotganini hisoblemiz
        paymentService.create(new PaymentRequest(order.getId(), true, order.getFineAmount() * overdueDays, false)); //  bu 3 kunga olsa 3 kunlik pulni to'lash kk shunda kitob 100 som bolsa 3 kunga 300 som tolanadi va u shunga saqlanadi

        return ResponseEntity.ok(new OrderResponse().convert(order));

    }


    @Override
    public ResponseEntity<List<OrderResponse>> viewAllOrders(Long userId) {
        return ResponseEntity.ok(orderRepository.findAllByUserEntityId(userId).stream().map(order -> new OrderResponse().convert(order)).collect(Collectors.toList()));
    }

    public ResponseEntity<OrderResponse> acceptOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ExceptionWithStatusCode(404, "Order not found"));
        order.setStatus(OrderStatus.ACCEPTED);
        return ResponseEntity.ok(new OrderResponse().convert(orderRepository.save(order)));
    }

    @Override
    public ResponseEntity<OrderResponse> returnOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ExceptionWithStatusCode(404, "Order not found"));

        if (order.getStatus() != OrderStatus.ACCEPTED) {
            throw new ExceptionWithStatusCode(400, "Only accepted orders can be returned");
        }

        order.setStatus(OrderStatus.RETURNED);
        order.setReturnedDate(LocalDate.now());

        if (order.getReturnedDate().isAfter(order.getEndDate())) {
            long overdueDays = ChronoUnit.DAYS.between(order.getEndDate(), order.getReturnedDate());
            order.setFineAmount(order.getBook().getPricePerDay() + ((order.getBook().getPricePerDay() * 0.01) * overdueDays));
            paymentService.create(new PaymentRequest(order.getId(), true, (order.getBook().getPricePerDay() * 0.01) * overdueDays, true)); // buyerda o'sha jarima
        } else {
            order.setFineAmount(0D);
        }
        bookService.updateStatus(order.getBook().getId(), false);

        orderRepository.save(order);
        return ResponseEntity.ok(new OrderResponse().convert(order));
    }

    @Override
    public boolean existsByUserIdAndBookIdAndStatusIn(Long userId, Long bookId, List<OrderStatus> statuses) {
        return orderRepository.existsByUserEntityIdAndBookIdAndStatusIn(userId, bookId, statuses);
    }

    @Override
    public ResponseEntity<List<OrderResponse>> getAll(OrderStatus orderStatus) {
        List<OrderResponse> content = orderRepository.findAll(getSpecifications(orderStatus), PageRequest.of(0, 1000)).map(new OrderResponse()::convert).getContent();
        return ResponseEntity.ok(content);
    }


    public Specification<Order> getSpecifications(OrderStatus orderStatus) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (orderStatus != null){
                predicates.add(criteriaBuilder.equal(root.get("status"), orderStatus.name()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


    @Scheduled(cron = "0 0 0 * * *") // H
    public void removeExpiredBookings() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<Order> expiredOrders = orderRepository.findExpiredBookings(yesterday,OrderStatus.BOOKED );

        for (Order order : expiredOrders) {

            orderRepository.delete(order);
            orderRepository.save(order);
        }

        System.out.println("Expired bookings cleaned: " + expiredOrders.size());
    }
}
