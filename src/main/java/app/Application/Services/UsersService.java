package app.Application.Services;
import  app.Application.Misc.PasswordEncoding;
import lombok.RequiredArgsConstructor;
import app.Application.Interfaces.CardRepository;
import app.Application.Interfaces.UsersRepository;
import app.Application.Classes.Card;
import org.springframework.stereotype.Service;
import app.Application.dto.CardInfoDto;
import app.Application.Classes.User;
import app.Application.dto.UsersUpDto;
import app.Application.dto.UsersInDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final CardRepository cardRepository;
    PasswordEncoding passwordEncoding = new PasswordEncoding();

    @Transactional(readOnly = true)
    public User findUsers(UsersInfo usersInfo){
        return usersRepository.findById(usersInfo.getUserId()).get();
    }

    @Transactional(readOnly = true)
    public boolean findUsersById(String id) {
        if(usersRepository.findById(id).isEmpty()){
            return false;
        }else {
            return true;
        }

    }
    @Transactional
    public String signup(UsersUpDto usersUpDto){
        usersUpDto.setPw(passwordEncoding.encode(usersUpDto.getPw()));
        return usersRepository.save(usersUpDto.toEntity()).getId();
    }
    @Transactional(readOnly = true)
    public boolean signin(UsersInDto usersInDto){
        String dbResultPw = usersRepository.getOne(usersInDto.getId()).getPw();
        String bodyResultPw = usersInDto.getPw();
        return passwordEncoding.matches(bodyResultPw, dbResultPw);
    }
    @Transactional(readOnly = true)
    public List<Card> findAllCard(UsersInfo usersInfo){
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
