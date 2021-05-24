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
public class OrderApi {
    private final CartOrderService cartOrderService;
    private final OrderService orderService;
    private final UsersService userService;
    private final UserInfo userInfo;
    private final BrickService brickService;

    Date now = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy ss:mm:HH");
    String nowDate = format.format(now);

    @PostMapping("/addOrder")
    @ResponseBody
    public void addOrder(
            @RequestParam(value = "brickUid[]") List<UUID> brickUid,
            @RequestParam(value = "count[]") List<Long> count,
            @RequestParam(value = "cardid") String cardid,
            @RequestParam(value = "addrUid") Long addrUid) {

        orderService.createOrder(brickUid, count, cardid, addrUid);

        orderService.addOrderlist(brickUid, count);

        brickService.updateCountBrick(brickUid, count);

    }

    @PostMapping("/cartOrder")
    @ResponseBody
    public void cartlistOrder(
            @RequestParam(value = "bookUid[]") List<UUID> brickUid,
            @RequestParam(value = "count[]") List<Long> count,
            @RequestParam(value = "cardid") String cardid,
            @RequestParam(value = "addrUid") Long addrUid
    ){

        orderService.createOrder(brickUid, count, cardid, addrUid);

        orderService.addOrderlist(brickUid, count);

        brickService.updateCountBrick(brickUid, count);

        cartOrderService.deleteCart();

    }
}
