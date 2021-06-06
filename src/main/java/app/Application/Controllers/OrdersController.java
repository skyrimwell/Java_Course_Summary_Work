package app.Application.Controllers;

import app.Application.Services.*;
import lombok.RequiredArgsConstructor;
import app.Application.Api.OrdersApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class OrdersController {
    private final UsersInfo usersInfo;
    private final OrdersService ordersService;
    private final UsersService usersService;
    private final BrickService brickservice;

    @GetMapping("/orders/addOrder")
    public String addOrder(@RequestParam(value = "brickUid") Long brickUid, @RequestParam(value="count") Long count , Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("cardInfo", usersService.findAllCard(usersInfo));
        model.addAttribute("brickInfo", brickservice.findBrickById(brickUid));
        model.addAttribute("count", count);

        return "orders/addOrder";
    }

    @GetMapping("/orders/addCartlistOrder")
    public String addCartlistOrder(@RequestParam(value = "brickUid[]") List<Long> brickUid, @RequestParam(value = "count[]") Long[] count , Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("cardInfo", usersService.findAllCard(usersInfo));
        model.addAttribute("brickInfo", brickservice.findBrickByArrayUid(brickUid));
        model.addAttribute("count", count);

        return "orders/addCartlistOrder";
    }
}
