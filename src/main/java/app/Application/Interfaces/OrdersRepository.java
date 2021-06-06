package app.Application.Interfaces;
import app.Application.Classes.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findAllByOrderByUidDesc();
    List<Orders> findAllByUsers_Id(String userId);
}
