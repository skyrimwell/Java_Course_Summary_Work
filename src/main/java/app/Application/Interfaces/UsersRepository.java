package app.Application.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.Application.Classes.User;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {
}
