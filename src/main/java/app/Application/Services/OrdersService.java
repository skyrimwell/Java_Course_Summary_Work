package app.Application.Services;
import app.Application.Classes.Brick;
import app.Application.Classes.Card;
import app.Application.Classes.Order;
import app.Application.Interfaces.OrdersRepository;
import app.Application.Interfaces.OrderlistRepository;
import app.Application.Classes.OrderlistMultiid;
import app.Application.Classes.User;
import app.Application.dto.OrdersCreateDto;
import app.Application.dto.OrdersSearchDto;
import app.Application.dto.OrderlistAddDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final BrickService brickService;
    private final UsersService userService;
    private final OrdersRepository ordersRepository;
    private final OrderlistRepository orderlistRepository;
    private final UsersInfo usersInfo;

    Date now = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy ss:mm:HH");
    String nowDate = format.format(now);

    @Transactional
    public void createOrder(List<Long> brickUid, List<Long> count, String cardId){
        int totalprice = 0;
        int index = 0;
        for (Long brickuid : brickUid) {
            System.out.println("123123123");
            Brick brick = new Brick();
            brick = brickService.findBrickById(brickuid);
            totalprice += (brick.getBrickPrice()*count.get(index));
            index++;
        }
        Long totalPrice = (long) totalprice;

        Card card = new Card();
        card = userService.findCardByCardId(cardId);

        User users = userService.findUsers(usersInfo);

        OrdersCreateDto ordersCreateDto = new OrdersCreateDto();
        ordersCreateDto.setUsers(users);
        ordersCreateDto.setDate(nowDate);
        ordersCreateDto.setCardId(cardId);
        ordersCreateDto.setCardType(card.getType());
        ordersCreateDto.setCardDate(card.getDatetime());
        ordersCreateDto.setAmount(totalPrice);
        ordersRepository.save(ordersCreateDto.toEntity());
    }
    @Transactional
    public void addOrderlist(List<Long> brickUid, List<Long> count){
        int index = 0;
        for (Long brickuid : brickUid) {
            System.out.println(brickUid +"asdasd"+ count);
            Order lastAddOrders = new Order();
            lastAddOrders = lastAddOrder();

            Brick brick = new Brick();
            brick = brickService.findBrickById(brickuid);

            OrderlistMultiid orderlistMultiid = new OrderlistMultiid();
            orderlistMultiid.setBrickUid(brickuid);
            orderlistMultiid.setOrdersUid(lastAddOrders.getUid());

            OrderlistAddDto orderlistAddDto = new OrderlistAddDto();
            orderlistAddDto.setBrick(brick);
            orderlistAddDto.setOrderlistMultiid(orderlistMultiid);
            orderlistAddDto.setOrders(lastAddOrders);
            orderlistAddDto.setCount(count.get(index));

            orderlistRepository.save(orderlistAddDto.toEntity());
            index++;
        }
    }
    @Transactional(readOnly = true)
    public Order lastAddOrder(){
        return ordersRepository.findAllByOrderByUidDesc().get(0);
    }

    @Transactional(readOnly = true)
    public List<OrdersSearchDto> orderSearch(){
        List<OrdersSearchDto> ordersSearchDto = new ArrayList<OrdersSearchDto>();
        List<Order> orders = ordersRepository.findAllByUsers_Id(usersInfo.getUserId());
        for (Order order : orders) {
            OrdersSearchDto osd = new OrdersSearchDto();
            osd.setOrderDate(order.getDate());
            osd.setBrickPrice(order.getAmount());
            ordersSearchDto.add(osd);
        }

        return ordersSearchDto;
    }
}
