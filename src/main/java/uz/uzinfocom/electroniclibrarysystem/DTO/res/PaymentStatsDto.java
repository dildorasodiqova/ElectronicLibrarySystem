package uz.uzinfocom.electroniclibrarysystem.DTO.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatsDto {
    private LocalDate date;
    private Double totalAmount;
}
