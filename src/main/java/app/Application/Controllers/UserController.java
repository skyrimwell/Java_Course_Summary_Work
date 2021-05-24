package app.Application.Controllers;
import app.Application.Services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UsersService userService;
    private final UserInfo userInfo;
    private final BrickService brickService;
    private final CartOrderService cartOrderService;
    private final OrderService orderService;

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("userid", userInfo.getUserId());
        model.addAttribute("brickInfo", brickService.findAllBricks());
        if(userInfo.getUserId() != null){
            if(userInfo.getUserId().equals("master")){
                model.addAttribute("master", userInfo.getUserId());
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
        model.addAttribute("userid", userInfo.getUserId());
        model.addAttribute("cardInfo", userService.findAllCard(userInfo));
        model.addAttribute("orderInfo", orderService.orderSearch());
        return "users/mypage";
    }

    @GetMapping("/users/cartOrder")
    public String cartlist(Model model){
        model.addAttribute("userid", userInfo.getUserId());
        if(cartOrderService.cartfindByUser() != null){
            model.addAttribute("cartOrderInfo", cartOrderService.findByCartuid());
        }
        return "users/cartOrder";
    }


}
