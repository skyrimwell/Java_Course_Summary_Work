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
        if(userInfo.getUserId() != null){
            if(userInfo.getUserId().equals("master")){
                model.addAttribute("master", userInfo.getUserId());
            }
        }
        if(userInfo.getUserId() == null){
            model.addAttribute("existSession", "notExistSession");
        }
        return "bricks/detailBrick";
    }
    @GetMapping("/briks/brickupdate")
    public String brikupdate(@RequestParam(value="uid") Long uid, Model model){
        model.addAttribute("userid", userInfo.getUserId());
        model.addAttribute("bookInfo", brickService.findBrickById(uid));
        return "books/updateBook";
    }

    @GetMapping("/bricks/brickSearch")
    public String briksearch(@RequestParam(value = "sn") String search, Model model){
        model.addAttribute("userid", userInfo.getUserId());
        model.addAttribute("bookInfo",brickService.findBrickByType(search));
        return "books/searchBook";
    }
}