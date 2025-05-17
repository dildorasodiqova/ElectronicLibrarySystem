package uz.uzinfocom.electroniclibrarysystem.DTO.res;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {
    Long orderId;
    Boolean paymentOrNot; //  true bolsa tolandi shu pul , false bolsa tolanmadi
    Double totalSumma;
    boolean fine; // bu jarima  true bo'lsa jarima bo'ladi false bo'lsa orderni o'zini narhi bo'ladi , jarimalarni korish uchun kk
}
