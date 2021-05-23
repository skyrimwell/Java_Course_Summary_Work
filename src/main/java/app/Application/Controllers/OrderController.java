package app.Application.Controllers;

import app.Application.Services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final UserInfo userInfo;
    private final OrderService orderService;
    private final UserService userService;
    private final BrickService brickservice;

    @GetMapping("/orders/addOrder")
    public String addOrder(@RequestParam(value = "brickUid") Long brickUid, @RequestParam(value="count") Long count ,Model model){
        model.addAttribute("userid", userInfo.getUserId());
        model.addAttribute("cardInfo", userService.findAllCard(userInfo));
        model.addAttribute("brickInfo", brickservice.findBrickById(brickUid));
        model.addAttribute("count", count);

        return "orders/addOrder";
    }

    @GetMapping("/orders/addCartlistOrder")
    public String addCartlistOrder(@RequestParam(value = "brickUid[]") List<Long> brickUid, @RequestParam(value = "count[]") Long[] count , Model model){
        model.addAttribute("userid", userInfo.getUserId());
        model.addAttribute("cardInfo", userService.findAllCard(userInfo));
        model.addAttribute("bookInfo", brickservice.findBrickByArrayUid(brickUid));
        model.addAttribute("count", count);

        return "orders/addCartlistOrder";
    }
}
