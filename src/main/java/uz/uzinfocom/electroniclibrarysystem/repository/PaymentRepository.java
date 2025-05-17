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
        DATE(p.created_at) AS date,    
        SUM(p.total_summa) AS totalAmount   
    FROM payment p                      
    WHERE p.payment_or_not = true       
      AND p.created_at >= :startDate   
      AND p.created_at <= :endDate      
    GROUP BY DATE(p.created_at)         
    ORDER BY DATE(p.created_at)         
    """, nativeQuery = true)
    List<Object[]> getLast7DayPayments(@Param("startDate") LocalDateTime startDate,
                                       @Param("endDate") LocalDateTime endDate);



}
