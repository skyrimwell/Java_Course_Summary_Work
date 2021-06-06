package app.Application.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import app.Application.Services.UsersInfo;
import app.Application.Services.BrickService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BrickController {

    private final BrickService brickService;
    private final UsersInfo usersInfo;

    @GetMapping("/bricks/bricksave")
    public String bricksave(Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        return "bricks/saveBrick";
    }

    @GetMapping("/bricks/brickdetail")
    public String brickdetail(@RequestParam(value = "uid") Long uid, Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("brickInfo", brickService.findBrickById(uid));
        if(usersInfo.getUserId() != null){
            if(usersInfo.getUserId().equals("master")){
                model.addAttribute("master", usersInfo.getUserId());
            }
        }
        if(usersInfo.getUserId() == null){
            model.addAttribute("existSession", "notExistSession");
        }
        return "bricks/detailBrick";
    }
    @GetMapping("/bricks/brickupdate")
    public String brickupdate(@RequestParam(value="uid") Long uid, Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("brickInfo", brickService.findBrickById(uid));
        return "bricks/updateBrick";
    }

    @GetMapping("/bricks/brickSearch")
    public String bricksearch(@RequestParam(value = "sn") String search, Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("brickInfo",brickService.findBrickByBrickType(search));
        return "bricks/searchBrick";
    }
}