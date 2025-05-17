package uz.uzinfocom.electroniclibrarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.uzinfocom.electroniclibrarysystem.entity.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByCreatedAtBetweenAndFineTrue(LocalDate from, LocalDate to);

    @Query(value = """
    SELECT 
        DATE(p.created_at) AS date,     -- 1. created_at ustunidan faqat sana qismini oladi (vaqtni emas)
        SUM(p.total_summa) AS totalAmount    -- 2. Har bir kun uchun jami tushgan summani hisoblaydi
    FROM payment p                       -- 3. `payment` dan olamiz
    WHERE p.payment_or_not = true        -- 4. faqat pul to'langanlarini
      AND p.created_at >= :startDate     -- shu kundan boshlab
      AND p.created_at <= :endDate       --shu kungacha 
    GROUP BY DATE(p.created_at)          --natijani har bir kun boyicha gruhledi
    ORDER BY DATE(p.created_at)          --sanalarni ketma ket chiqaradi eski sanadan yangisiga qarab o'sish tartibida
    """, nativeQuery = true)
    List<Object[]> getLast7DayPayments(@Param("startDate") LocalDateTime startDate,
                                       @Param("endDate") LocalDateTime endDate);



}
