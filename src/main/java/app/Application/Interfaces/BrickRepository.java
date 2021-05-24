package app.Application.Interfaces;

import app.Application.Classes.Brick;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository("BrickRepository")
public interface BrickRepository extends JpaRepository<Brick,UUID> {
    List<Brick> findBrickByType(String type);
    List<Brick> findBrickBySize(double size);

    @NotNull List<Brick> findAll();
    void deleteById(UUID uid);


}

