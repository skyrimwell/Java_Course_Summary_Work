package app.Application.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.Application.Classes.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
}
