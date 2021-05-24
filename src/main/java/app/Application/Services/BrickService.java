package app.Application.Services;

import app.Application.Classes.Brick;
import app.Application.Interfaces.BrickRepository;
import app.Application.Interfaces.CartRepository;
import app.Application.dto.BrickUpdateCountDto;
import app.Application.dto.BrickUpdateDto;
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
    public Brick findBrickById(UUID uid){
        return brickRepository.findById(uid).get();
    }

    @Transactional
    public void  updateBrick(UUID bookUid, BrickUpdateDto brickUpdateDto){
        findBrickById(bookUid).updateBrick(brickUpdateDto);
    }

    @Transactional
    public void deleteBrick(UUID uid){
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
    public void updateCountBrick(List<UUID> brickUid, List<Long> count){
        int index = 0;
        for (UUID brickuid : brickUid) {
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
    public List<Brick> findBrickByArrayUid(List<UUID> brickUid){
        List<Brick> arrBook = new ArrayList<Brick>();
        for (UUID uid : brickUid) {
            arrBook.add(brickRepository.getOne(uid));
        }
        return arrBook;
    }
}
