package uz.uzinfocom.electroniclibrarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.uzinfocom.electroniclibrarysystem.entity.Order;
import uz.uzinfocom.electroniclibrarysystem.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long user_id);

    @Query("SELECT o FROM Order o WHERE o.status = 'BOOKED' AND o.startDate < :yesterday")
    List<Order> findExpiredBookings(@Param("yesterday") LocalDate yesterday);

    boolean existsByUserIdAndBookIdAndStatusIn(Long userId, Long bookId, List<OrderStatus> statuses);

}
