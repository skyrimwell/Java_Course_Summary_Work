package app.Application.Api;

import app.Application.Services.*;
import app.Application.dto.CardInfoDto;
import app.Application.dto.UsersInDto;
import app.Application.dto.UsersUpDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Api(value = "Users", description = "\n" +
        "Users management", tags = {"templates/users"})
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UsersApi {
    private final UsersService usersService;
    private final UsersInfo usersInfo;

    @ApiOperation(value = "\n" +
            "Войти")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsersInDto usersInDto){
        ApiResponse result = null;
        try {
            if (usersService.signin(usersInDto)){
                result = new ApiResponse(true, "\n" +
                        "success", usersService.signin(usersInDto));
                usersInfo.setUserId(usersInDto.getId());
                return ResponseEntity.ok().body(result);
            }else {
                result = new ApiResponse(true, "false", usersService.signin(usersInDto));
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
    public ResponseEntity<?> signupPosts(@RequestBody UsersUpDto usersUpDto){
        ApiResponse result = null;
        try {
            if(!usersService.findUsersById(usersUpDto.getId())){
                result = new ApiResponse(true, "\n" +
                        "success", usersService.signup(usersUpDto));
                return ResponseEntity.ok().body(result);
            }else{
                result = new ApiResponse(false, "\n" +
                        "false", usersService.findUsersById(usersUpDto.getId()));
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
            cardInfoDto.setUsers(usersService.findUsers(usersInfo));
            result = new ApiResponse(true, "\n" +
                    "success", usersService.addCard(cardInfoDto));
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
        usersService.deleteCard(cardid);
        return new RedirectView("/templates/users/mypage");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ApiResponse result = null;
        usersInfo.setUserId(null);
        result = new ApiResponse(true, "\n" +
                "success", usersInfo.getUserId());
        return ResponseEntity.ok().body(result);
    }
}
