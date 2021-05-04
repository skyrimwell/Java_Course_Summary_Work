package app.Application.Controllers;

import app.Application.Items.Brick;

import app.Application.Services.BrickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
public class BrickController {
    @Autowired
    private BrickService brickService;

    @PostMapping("/bricks")
    public void addUser(@RequestBody Brick brick) {
        brickService.addBrick(brick);
    }

    @GetMapping("/phoness")
    public List<Brick> getAll() {
        return brickService.getPhones();
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable UUID id) {
        brickService.deletePhone(id);
    }

    @GetMapping("/getPhoneByName/{firstName}")
    public List<Brick> getByName(@PathVariable String type){
        return brickService.getByType(type);
    }

    @GetMapping("/getPhoneByOwner/{owner}")
    public List<Brick> getByLastName(@PathVariable double size){
        return brickService.getBySize(size);
    }
}