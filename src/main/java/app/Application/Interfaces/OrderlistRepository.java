package app.Application.Interfaces;
import app.Application.Classes.Orderlist;
import app.Application.Classes.OrderlistMultiid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderlistRepository extends JpaRepository<Orderlist, OrderlistMultiid> {

}
