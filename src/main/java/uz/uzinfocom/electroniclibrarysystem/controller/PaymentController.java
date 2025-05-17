package uz.uzinfocom.electroniclibrarysystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.PaymentStatsDto;
import uz.uzinfocom.electroniclibrarysystem.entity.Payment;
import uz.uzinfocom.electroniclibrarysystem.service.paymentService.PaymentService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @GetMapping("/fine/by-date")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    public ResponseEntity<List<Payment>> getFinePaymentsByDate(  /// shu vaqt orasidagi jarimalarni korish
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        List<Payment> payments = paymentService.getAllFinesBetweenDates(from, to);
        return ResponseEntity.ok(payments);
    }


    @GetMapping("/stats/last-7-days")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<PaymentStatsDto>> getLast7DaysStats() { //statistikani koradi otgan 7 kunlik
        List<PaymentStatsDto> stats = paymentService.getLast7DaysStats();
        return ResponseEntity.ok(stats);
    }

}
