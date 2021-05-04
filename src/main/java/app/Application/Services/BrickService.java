package app.Application.Services;

import app.Application.Items.Brick;
import app.Application.Interfaces.BrickRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@Slf4j
public class BrickService {
    @Autowired
    private final BrickRepository brickRepository;

    public BrickService(BrickRepository brickRepository) {
        this.brickRepository = brickRepository;
    }

    public void addBrick(Brick brick) {
        brickRepository.save(brick);
    }

    public List<Brick> getPhones() {
        return brickRepository.findAll();
    }

    public void deletePhone(UUID id) {
        brickRepository.deleteById(id);
    }

    public List<Brick> getByType(String type) {
        return brickRepository.findAllByType(type);
    }

    public List<Brick> getBySize(double size) {
        return brickRepository.findAllBySize(size);
    }
}
