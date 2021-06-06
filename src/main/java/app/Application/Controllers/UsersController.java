package app.Application.Controllers;
import app.Application.Services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UsersController {
    private final UsersService usersService;
    private final UsersInfo usersInfo;
    private final BrickService brickService;
    private final CartOrderService cartOrderService;
    private final OrdersService ordersService;

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("brickInfo", brickService.findAllBricks());
        if(usersInfo.getUserId() != null){
            if(usersInfo.getUserId().equals("master")){
                model.addAttribute("master", usersInfo.getUserId());
            }
        }
        return "main";
    }
    @GetMapping("/users/login")
    public String login() {return "users/login";}

    @GetMapping("/users/signup")
    public String signup() {return "users/signup";}

    @GetMapping("/users/mypage")
    public String mypage(Model model)
    {
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("cardInfo", usersService.findAllCard(usersInfo));
        model.addAttribute("orderInfo", ordersService.orderSearch());
        return "users/mypage";
    }

    @GetMapping("/users/cartOrder")
    public String cartlist(Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        if(cartOrderService.cartfindByUser() != null){
            model.addAttribute("cartOrderInfo", cartOrderService.findByCartuid());
        }
        return "users/cartOrder";
    }


}
