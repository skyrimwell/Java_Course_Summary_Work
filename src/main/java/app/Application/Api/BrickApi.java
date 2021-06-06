package app.Application.Api;

import app.Application.Services.*;
import app.Application.dto.BrickSaveDto;
import app.Application.dto.BrickUpdateDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Api(value = "bricks", description = "\n" +
        "Brick management", tags = { "\n" +
        "bricks"})
@RequestMapping("/bricks")
@RestController
@RequiredArgsConstructor
public class BrickApi {
    private final BrickService brickService;

    @ApiOperation(value = "Brick registration")
    @PostMapping("/saveBricks")
    public ResponseEntity<?> saveBricks(@RequestBody BrickSaveDto brickSaveDto){
        ApiResponse result = null;
        try {
            result = new ApiResponse(true, "\n" + "success", brickService.saveBrick(brickSaveDto));
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @ApiOperation(value = "\n" + "Brick modification")
    @PostMapping("/updateBricks/{uid}")
    public ResponseEntity<?> updateBricks(@PathVariable("uid") Long uid, @RequestBody BrickUpdateDto brickUpdateDto){
        ApiResponse result = null;
        brickUpdateDto.setUid(uid);
        try {
            brickService.updateBrick(uid, brickUpdateDto);
            result = new ApiResponse(true, "\n" + "success",brickService.findBrickById(uid));
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @ApiOperation(value = "\n" + "Delete brick")
    @PostMapping("/deleteBricks/{uid}")
    public RedirectView deleteBricks(@PathVariable("uid") Long uid){
        brickService.deleteBrick(uid);
        return new RedirectView("/");
    }
}
