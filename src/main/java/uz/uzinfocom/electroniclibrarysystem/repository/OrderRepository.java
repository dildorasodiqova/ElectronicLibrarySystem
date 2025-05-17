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
    List<Order> findAllByUserEntityId(Long user_id);

    @Query("SELECT o FROM Order o WHERE o.status = :status  AND o.startDate < :yesterday")
    List<Order> findExpiredBookings(@Param("yesterday") LocalDate yesterday, @Param("status") OrderStatus status);

    boolean existsByUserEntityIdAndBookIdAndStatusIn(Long userId, Long bookId, List<OrderStatus> statuses);

}
