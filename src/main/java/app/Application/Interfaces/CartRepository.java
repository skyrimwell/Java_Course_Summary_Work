package app.Application.Interfaces;
import app.Application.Classes.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUsers_Id(String id);
}