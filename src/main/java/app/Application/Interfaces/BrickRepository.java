package app.Application.Interfaces;

import app.Application.Classes.Brick;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository("BrickRepository")
public interface BrickRepository extends JpaRepository<Brick,Long> {
    List<Brick> findBrickByBrickType(String brickType);
    List<Brick> findBrickByBrickSize(double brickSize);

    @NotNull List<Brick> findAll();
    void deleteById(Long uid);


}

