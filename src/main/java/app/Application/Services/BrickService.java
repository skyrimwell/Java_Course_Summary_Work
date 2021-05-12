package app.Application.Services;

import app.Application.Classes.Brick;
import app.Application.Interfaces.BrickRepository;
import app.Application.Interfaces.CartRepository;
import app.Application.dto.BrickUpdateDto;
import app.Application.Services.UserInfo;
import app.Application.dto.BrickInfoDto;
import app.Application.dto.BrickSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class BrickService  {
    private final BrickRepository brickRepository;

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


    public BrickService(BrickRepository brickRepository) {
        this.brickRepository = brickRepository;
    }

    @Transactional(readOnly = true)
    public List<Brick> findBrickByType(String type){
        return brickRepository.findBrickByType(type);
    }
    @Transactional(readOnly = true)
    public List<Brick> findBrickBySize(double size){
        return brickRepository.findBrickBySize(size);
    }
}
