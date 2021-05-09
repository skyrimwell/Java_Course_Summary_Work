package app.Application.Misc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String message) {
        super(String.format("Пользователь '%s' уже существует", message));
    }
}
