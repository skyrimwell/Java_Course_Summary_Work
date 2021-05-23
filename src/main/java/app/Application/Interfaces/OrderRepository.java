package app.Application.Interfaces;
import app.Application.Classes.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByOrderByUidDesc();
    List<Order> findAllByUsers_Id(String userId);
}
