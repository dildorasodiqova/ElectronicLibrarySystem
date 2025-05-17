package uz.uzinfocom.electroniclibrarysystem.DTO.req;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequest {
    @NotNull(message = "Order ID is required")
    Long orderId;

    @NotNull(message = "Payment status must be specified")
    Boolean paymentOrNot; // true - to‘landi, false - to‘lanmadi

    @NotNull(message = "Total summa is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Total summa must be zero or positive")
    Double totalSumma;
    boolean fine; // bu jarima  true bo'lsa jarima bo'ladi false bo'lsa orderni o'zini narhi bo'ladi , jarimalarni korish uchun kk
}

