package app.Application.Services;

import app.Application.Interfaces.BrickRepository;
import app.Application.Classes.Cart;
import app.Application.Interfaces.CartRepository;
import app.Application.Classes.CartOrder;
import app.Application.Services.UserInfo;
import app.Application.Interfaces.CartOrderRepository;
import app.Application.Misc.MultiId;
import app.Application.dto.CartCreateDto;
import app.Application.dto.CartOrderAddDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartOrderService {
    private final UserInfo userInfo;
    private final CartRepository cartRepository;
    private final BrickRepository brickRepository;
    private final CartOrderRepository cartOrderRepository;
    private final UserService userService;
    private final BrickService bookService;

    Date now = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy ss:mm:HH");
    String nowDate = format.format(now);

    @Transactional(readOnly = true)
    public Cart cartfindByUser(){
        return cartRepository.findByUsers_Id(userInfo.getUserId());
    }

    @Transactional(readOnly = true)
    public boolean existCart(){
        if(cartRepository.findByUsers_Id(userInfo.getUserId()) != null){
            return true;
        }else {
            return false;
        }
    }
    @Transactional
    public String createCart(){
        CartCreateDto cartCreateDto = new CartCreateDto();
        cartCreateDto.setCreatetime(nowDate);
        cartCreateDto.setModifytime(nowDate);
        cartCreateDto.setUser(userService.findUsers(userInfo));

        return cartRepository.save(cartCreateDto.toEntity()).toString();
    }

    @Transactional
    public void deleteCart(){
        Cart cart = cartfindByUser();
        cartRepository.delete(cart);
    }

    @Transactional
    public String addCartOrder(Long brickUid, CartOrderAddDto cartOrderAddDto){
        Long cartUid = cartRepository.findByUsers_Id(userInfo.getUserId()).getUid();
        MultiId multiId = new MultiId();
        multiId.setCartUid(cartUid);
        multiId.setBookUid(brickUid);
        cartOrderAddDto.setMultiId(multiId);
        cartOrderAddDto.setBrick(bookService.findBrickById(brickUid));
        cartOrderAddDto.setCart(cartfindByUser());

        return cartOrderRepository.save(cartOrderAddDto.toEntity()).toString();
    }
    @Transactional
    public void updateModifyTimeInCart(){
        Cart cart = cartfindByUser();
        cart.updateModifytime(nowDate);
    }

    @Transactional(readOnly = true)
    public List<CartOrder> findByCartuid(){
        Long cartUid = cartfindByUser().getUid();
        return cartOrderRepository.findAllByCart_Uid(cartUid);
    }

    @Transactional
    public void deleteCartOrder(Long brickUid){
        Cart cart = cartfindByUser();
        MultiId mId = new MultiId();
        mId.setCartUid(cart.getUid());
        mId.setBookUid(brickUid);
        cartOrderRepository.deleteById(mId);
    }
}