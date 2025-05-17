package uz.uzinfocom.electroniclibrarysystem.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Payment extends BaseModel{
    @ManyToOne
    Order order;

    Boolean paymentOrNot; //  true bolsa tolandi shu pul , false bolsa tolanmadi
    Double totalSumma; // qancha tolangani
    boolean fine; // bu jarima  true bo'lsa jarima bo'ladi false bo'lsa orderni o'zini narhi bo'ladi , jarimalarni korish uchun kk

}
