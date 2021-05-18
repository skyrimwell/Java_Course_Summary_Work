package app.Application.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.Application.Classes.User;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
