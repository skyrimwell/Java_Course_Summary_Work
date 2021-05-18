package app.Application.Services;

import app.Application.Classes.Brick;
import app.Application.Classes.Cart;
import app.Application.Interfaces.BrickRepository;
import app.Application.Interfaces.CartRepository;
import app.Application.dto.BrickUpdateCountDto;
import app.Application.dto.BrickUpdateDto;
import app.Application.Services.UserInfo;
import app.Application.dto.BrickInfoDto;
import app.Application.dto.BrickSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BrickService  {
    private final BrickRepository brickRepository;
    private final CartRepository cartRepository;
    private final UserInfo usersInfo;

    @Transactional
    public String saveBrick(BrickSaveDto brickSaveDto){
        return brickRepository.save(brickSaveDto.toEntity()).toString();
    }

    @Transactional(readOnly = true)
    public List<Brick> findAllBricks(){
        return brickRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Brick findBrickById(Long uid){
        return brickRepository.findById(uid).get();
    }

    @Transactional
    public void  updateBrick(Long bookUid, BrickUpdateDto brickUpdateDto){
        findBrickById(bookUid).updateBrick(brickUpdateDto);
    }

    @Transactional
    public void deleteBrick(Long uid){
        brickRepository.delete(findBrickById(uid));
    }

    @Transactional(readOnly = true)
    public List<Brick> findBrickByType(String type){
        return brickRepository.findBrickByType(type);
    }

    @Transactional(readOnly = true)
    public List<Brick> findBrickBySize(double size){
        return brickRepository.findBrickBySize(size);
    }

    @Transactional
    public void updateCountBrick(List<Long> brickUid, List<Long> count){
        int index = 0;
        for (Long brickuid : brickUid) {
            System.out.println("Book count update");
            Brick brick = new Brick();
            brick = findBrickById(brickuid);
            Long updateBrickCount = brick.getBrickcount() - count.get(index);
            BrickUpdateCountDto brickUpdateCountDto = new BrickUpdateCountDto();
            brickUpdateCountDto.setBrickCount(updateBrickCount);
            findBrickById(brickuid).updateCount(brickUpdateCountDto);
            index++;
        }
    }
    @Transactional(readOnly = true)
    public List<Brick> findBrickByArrayUid(List<Long> brickUid){
        List<Brick> arrBook = new ArrayList<Brick>();
        for (Long uid : brickUid) {
            arrBook.add(brickRepository.getOne(uid));
        }
        return arrBook;
    }
}
