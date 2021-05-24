package app.Application.Services;
import app.Application.Classes.Brick;
import app.Application.Classes.Card;
import app.Application.Classes.Order;
import app.Application.Interfaces.OrderRepository;
import app.Application.Interfaces.OrderlistRepository;
import app.Application.Classes.OrderlistMultiid;
import app.Application.Classes.User;
import app.Application.dto.OrderCreateDto;
import app.Application.dto.OrderSearchDto;
import app.Application.dto.OrderlistAddDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final BrickService brickService;
    private final UsersService userService;
    private final OrderRepository orderRepository;
    private final OrderlistRepository orderlistRepository;
    private final UserInfo userInfo;

    Date now = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy ss:mm:HH");
    String nowDate = format.format(now);

    @Transactional
    public void createOrder(List<UUID> brickUid, List<Long> count, String cardId, Long addrId){
        int totalprice = 0;
        int index = 0;
        for (UUID brickuid : brickUid) {
            System.out.println("123123123");
            Brick brick = new Brick();
            brick = brickService.findBrickById(brickuid);
            totalprice += (brick.getPrice()*count.get(index));
            index++;
        }
        Long totalPrice = (long) totalprice;

        Card card = new Card();
        card = userService.findCardByCardId(cardId);

        User user = userService.findUsers(userInfo);

        OrderCreateDto orderCreateDto = new OrderCreateDto();
        orderCreateDto.setUser(user);
        orderCreateDto.setDate(nowDate);
        orderCreateDto.setCardId(cardId);
        orderCreateDto.setCardType(card.getType());
        orderCreateDto.setCardDate(card.getDatetime());
        orderCreateDto.setAmount(totalPrice);
        orderRepository.save(orderCreateDto.toEntity());
    }
    @Transactional
    public void addOrderlist(List<UUID> brickUid, List<Long> count){
        int index = 0;
        for (UUID brickuid : brickUid) {
            System.out.println(brickUid +"asdasd"+ count);
            Order lastAddOrder = new Order();
            lastAddOrder = lastAddOrder();

            Brick brick = new Brick();
            brick = brickService.findBrickById(brickuid);

            OrderlistMultiid orderlistMultiid = new OrderlistMultiid();
            orderlistMultiid.setBrickUid(brickuid);
            orderlistMultiid.setOrdersUid(lastAddOrder.getUid());

            OrderlistAddDto orderlistAddDto = new OrderlistAddDto();
            orderlistAddDto.setBrick(brick);
            orderlistAddDto.setOrderlistMultiid(orderlistMultiid);
            orderlistAddDto.setOrder(lastAddOrder);
            orderlistAddDto.setCount(count.get(index));

            orderlistRepository.save(orderlistAddDto.toEntity());
            index++;
        }
    }
    @Transactional(readOnly = true)
    public Order lastAddOrder(){
        return orderRepository.findAllByOrderByUidDesc().get(0);
    }

    @Transactional(readOnly = true)
    public List<OrderSearchDto> orderSearch(){
        List<OrderSearchDto> orderSearchDto = new ArrayList<OrderSearchDto>();
        List<Order> orders = orderRepository.findAllByUsers_Id(userInfo.getUserId());
        for (Order order : orders) {
            OrderSearchDto osd = new OrderSearchDto();
            osd.setOrderDate(order.getDate());
            osd.setPrice(order.getAmount());
            orderSearchDto.add(osd);
        }

        return orderSearchDto;
    }
}
