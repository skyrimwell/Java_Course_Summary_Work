package app.Application.Api;

import app.Application.Services.*;
import app.Application.dto.CardInfoDto;
import app.Application.dto.UserInDto;
import app.Application.dto.UserUpDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Api(value = "Users", description = "\n" +
        "Users management", tags = { "users" })
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserApi {
    private final UsersService userService;
    private final UserInfo userInfo;

    @ApiOperation(value = "\n" +
            "Войти")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserInDto userInDto){
        ApiResponse result = null;
        try {
            if (userService.signin(userInDto)){
                result = new ApiResponse(true, "\n" +
                        "success", userService.signin(userInDto));
                userInfo.setUserId(userInDto.getId());
                return ResponseEntity.ok().body(result);
            }else {
                result = new ApiResponse(true, "false", userService.signin(userInDto));
                return ResponseEntity.badRequest().body(result);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(result);
        }
    }


    @ApiOperation(value = "\n" +
            "\n" +
            "Register now")
    @PostMapping("/signup")
    public ResponseEntity<?> signupPosts(@RequestBody UserUpDto userUpDto){
        ApiResponse result = null;
        try {
            if(!userService.findUsersById(userUpDto.getId())){
                result = new ApiResponse(true, "\n" +
                        "success", userService.signup(userUpDto));
                return ResponseEntity.ok().body(result);
            }else{
                result = new ApiResponse(false, "\n" +
                        "false", userService.findUsersById(userUpDto.getId()));
                return ResponseEntity.badRequest().body(result);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @ApiOperation(value = "\n" +
            "\n" +
            "Add card")
    @PostMapping("/addCard")
    public ResponseEntity<?> addCard(@RequestBody CardInfoDto cardInfoDto){
        ApiResponse result = null;
        try {
            cardInfoDto.setUser(userService.findUsers(userInfo));
            result = new ApiResponse(true, "\n" +
                    "success", userService.addCard(cardInfoDto));
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(result);
        }

    }

    @ApiOperation(value = "\n" +
            "Delete card")
    @PostMapping("/deleteCard/{cardId}")
    public RedirectView deleteCard(@PathVariable("cardId") String cardid){
        userService.deleteCard(cardid);
        return new RedirectView("/users/mypage");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ApiResponse result = null;
        userInfo.setUserId(null);
        result = new ApiResponse(true, "\n" +
                "success", userInfo.getUserId());
        return ResponseEntity.ok().body(result);
    }
}
