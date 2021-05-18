package app.Application.Interfaces;
import org.springframework.data.jpa.repository.JpaRepository;
import app.Application.Classes.CartOrder;
import app.Application.Misc.MultiId;
import java.util.List;

public interface CartOrderRepository extends JpaRepository<CartOrder, MultiId> {
    List<CartOrder> findAllByCart_Uid(Long uid);
}
