package app.Application.Services;
import  app.Application.Misc.PasswordEncoding;
import lombok.RequiredArgsConstructor;
import app.Application.Interfaces.CardRepository;
import app.Application.Interfaces.UserRepository;
import app.Application.Classes.Card;
import org.springframework.stereotype.Service;
import app.Application.dto.CardInfoDto;
import app.Application.dto.RegisterDto;
import app.Application.Misc.UserAlreadyExists;
import app.Application.Classes.User;
import app.Application.dto.UserUpDto;
import app.Application.dto.CardInfoDto;
import app.Application.dto.UserInDto;
import app.Application.Services.UserInfo;
import app.Application.Classes.UserStats;
import app.Application.Interfaces.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    PasswordEncoding passwordEncoding = new PasswordEncoding();

    @Transactional(readOnly = true)
    public User findUsers(UserInfo userInfo){
        return userRepository.findById(userInfo.getUserId()).get();
    }

    @Transactional(readOnly = true)
    public boolean findUsersById(String id) {
        if(userRepository.findById(id).isEmpty()){
            return false;
        }else {
            return true;
        }

    }
    @Transactional
    public String signup(UserUpDto userUpDto){
        userUpDto.setPw(passwordEncoding.encode(userUpDto.getPw()));
        return userRepository.save(userUpDto.toEntity()).getId();
    }
    @Transactional(readOnly = true)
    public boolean signin(UserInDto userInDto){
        String dbResultPw = userRepository.getOne(userInDto.getId()).getPw();
        String bodyResultPw = userInDto.getPw();
        return passwordEncoding.matches(bodyResultPw, dbResultPw);
    }
    @Transactional(readOnly = true)
    public List<Card> findAllCard(UserInfo usersInfo){
        return cardRepository.findAllByUsers_Id(usersInfo.getUserId());
    }
    @Transactional(readOnly = true)
    public Card findCardByCardId(String cardId){
        return cardRepository.findById(cardId).get();
    }
    @Transactional
    public String addCard(CardInfoDto cardInfoDto){
        return cardRepository.save(cardInfoDto.toEntity()).getId();
    }
    @Transactional
    public void deleteCard(String cardId) {
        cardRepository.delete(cardRepository.findById(cardId).get());
    }
}
