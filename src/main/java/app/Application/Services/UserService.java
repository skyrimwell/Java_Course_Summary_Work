package app.Application.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.Application.dto.RegisterDto;
import app.Application.Misc.UserAlreadyExists;
import app.Application.Classes.User;
import app.Application.Classes.UserStats;
import app.Application.Interfaces.UserRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void addNewUser(RegisterDto request) throws UserAlreadyExists {
        String username = request.getUsername();
        if (userExists(username)) {
            throw new UserAlreadyExists(username);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @Transactional(Transactional.TxType.MANDATORY)
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Пользователь не найден: " + username)
                );
        return new UserStats(user);
    }
}
