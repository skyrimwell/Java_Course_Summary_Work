package app.Application.Api;

import app.Application.Api.*;
import app.Application.Services.BrickService;
import app.Application.Services.CartOrderService;
import app.Application.dto.CartOrderAddDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Api(value = "Cart", description = "Cart management", tags = { "Cart" })
@RequestMapping("/cart")
@RestController
@RequiredArgsConstructor
public class CartOrderApi {
    private final CartOrderService cartOrderService;
    private final BrickService brickService;

    @ApiOperation(value = "Add to Shopping Cart")
    @PostMapping("/addCartOrder/{bookUid}")
    public ResponseEntity<?> addCartlist(@PathVariable(value = "brickUid") UUID brickUid, @RequestBody CartOrderAddDto cartOrderAddDto) {
        ApiResponse result = null;
        try {
            if (cartOrderService.existCart() == true){
                System.out.println("Exist Cart");
                result = new ApiResponse(true, "success", cartOrderService.addCartOrder(brickUid, cartOrderAddDto));
                cartOrderService.updateModifyTimeInCart();
                return ResponseEntity.ok().body(result);
            }else {
                System.out.println("Not Extist Cart");
                cartOrderService.createCart();
                result = new ApiResponse(true, "\n" + "success", cartOrderService.addCartOrder(brickUid, cartOrderAddDto));
                return ResponseEntity.ok().body(result);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping(value = "/deleteCartlist")
    public void deleteCartlist(@RequestParam(value = "checkArr[]") List<UUID> valueArr){
        for (UUID string : valueArr) {
            cartOrderService.deleteCartOrder(string);
        }
    }



}

