package app.Application.Api;

import app.Application.Services.*;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Api(value = "Order", description = "\n" + "Order management", tags = { "Order" })
@RequestMapping("/orders")
@RestController
@RequiredArgsConstructor
public class OrdersApi {
    private final CartOrderService cartOrderService;
    private final OrdersService ordersService;
    private final UsersService userService;
    private final UsersInfo usersInfo;
    private final BrickService brickService;

    Date now = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy ss:mm:HH");
    String nowDate = format.format(now);

    @PostMapping("/addOrder")
    @ResponseBody
    public void addOrder(
            @RequestParam(value = "brickUid[]") List<Long> brickUid,
            @RequestParam(value = "count[]") List<Long> count,
            @RequestParam(value = "cardid") String cardid) {

        ordersService.createOrder(brickUid, count, cardid);

        ordersService.addOrderlist(brickUid, count);

        brickService.updateCountBrick(brickUid, count);

    }

    @PostMapping("/cartOrder")
    @ResponseBody
    public void cartlistOrder(
            @RequestParam(value = "brickUid[]") List<Long> brickUid,
            @RequestParam(value = "count[]") List<Long> count,
            @RequestParam(value = "cardid") String cardid
    ){

        ordersService.createOrder(brickUid, count, cardid);

        ordersService.addOrderlist(brickUid, count);

        brickService.updateCountBrick(brickUid, count);

        cartOrderService.deleteCart();

    }
}
