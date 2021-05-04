package app.Application.Interfaces;

import app.Application.Items.Brick;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("BrickRepository")
public interface BrickRepository extends JpaRepository<Brick,Long> {
    List<Brick> findAllByType(String type);
    List<Brick> findAllBySize(double size);

    @NotNull List<Brick> findAll();
    void deleteById(UUID id);
}

