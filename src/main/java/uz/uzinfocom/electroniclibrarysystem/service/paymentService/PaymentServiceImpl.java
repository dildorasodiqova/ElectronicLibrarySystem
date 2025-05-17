package uz.uzinfocom.electroniclibrarysystem.service.paymentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.PaymentRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.PaymentStatsDto;
import uz.uzinfocom.electroniclibrarysystem.entity.Order;
import uz.uzinfocom.electroniclibrarysystem.entity.Payment;
import uz.uzinfocom.electroniclibrarysystem.exception.ExceptionWithStatusCode;
import uz.uzinfocom.electroniclibrarysystem.repository.OrderRepository;
import uz.uzinfocom.electroniclibrarysystem.repository.PaymentRepository;
import uz.uzinfocom.electroniclibrarysystem.service.orderservice.OrderService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    @Override
    public void create(PaymentRequest paymentRequest) {
        Order order = orderRepository.findById(paymentRequest.getOrderId()).orElseThrow(() -> new ExceptionWithStatusCode(400, "Order not found"));
        paymentRepository.save(new Payment(order, paymentRequest.getPaymentOrNot(), paymentRequest.getTotalSumma(), paymentRequest.isFine()));
    }

    public List<Payment> getAllFinesBetweenDates(LocalDate from, LocalDate to) {
        LocalDateTime startDateTime = from.atStartOfDay(); // 00:00:00
        LocalDateTime endDateTime = to.atTime(LocalTime.MAX); // 23:59:59.999999999

        return paymentRepository.findAllByCreatedAtBetweenAndFineTrue(LocalDate.from(startDateTime), LocalDate.from(endDateTime));
    }


    public List<PaymentStatsDto> getLast7DaysStats() {
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAgo = today.minusDays(6); // 6 kun orqaga, bugun bilan birga 7 kun

        LocalDateTime start = sevenDaysAgo.atStartOfDay();
        LocalDateTime end = today.atTime(LocalTime.MAX);

        List<Object[]> rawStats = paymentRepository.getLast7DayPayments(start, end);

        // Convert to DTO
        Map<LocalDate, Double> statsMap = rawStats.stream()
                .collect(Collectors.toMap(
                        row -> ((LocalDate) row[0]),
                        row -> ((BigDecimal) row[1]).doubleValue()
                ));

        // Har bir kun uchun bor yoki yo‘q summani tekshirib, ro‘yxatni to‘ldirish
        List<PaymentStatsDto> result = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            LocalDate date = sevenDaysAgo.plusDays(i);
            Double amount = statsMap.getOrDefault(date, 0.0);
            result.add(new PaymentStatsDto(date, amount));
        }

        return result;
    }

}
