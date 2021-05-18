package app.Application.Interfaces;
import app.Application.Classes.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, String> {
    List<Card> findAllByUsers_Id(String userid);
}

