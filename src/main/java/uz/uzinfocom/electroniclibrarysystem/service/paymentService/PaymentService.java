package uz.uzinfocom.electroniclibrarysystem.service.paymentService;

import uz.uzinfocom.electroniclibrarysystem.DTO.req.PaymentRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.PaymentStatsDto;
import uz.uzinfocom.electroniclibrarysystem.entity.Payment;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    void create(PaymentRequest paymentRequest);
    List<Payment> getAllFinesBetweenDates(LocalDate from, LocalDate to);
    List<PaymentStatsDto> getLast7DaysStats();
}
