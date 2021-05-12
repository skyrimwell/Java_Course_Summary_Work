package app.Application.Controllers;

import app.Application.Classes.Brick;
import org.springframework.ui.Model;
import app.Application.Services.UserInfo;
import app.Application.Services.BrickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
public class BrickController {

    private BrickService brickService;
    private UserInfo userInfo;
    @GetMapping("/bricks/bricksave")
    public String booksave(Model model){
        model.addAttribute("userid", userInfo.getUserId());
        return "bricks/saveBrick";
    }

    @GetMapping("/bricks/brickdetail")
    public String brickdetail(@RequestParam(value = "uid") Long uid, Model model){
        model.addAttribute("userid", userInfo.getUserId());
        model.addAttribute("brickInfo", brickService.findBrickById(uid));
        
    }


    @PostMapping("/bricks")
    public void addUser(@RequestBody Brick brick) {
        brickService.addBrick(brick);
    }

    @GetMapping("/bricks")
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