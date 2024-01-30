package ru.mavrin.bankapi.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mavrin.bankapi.model.User;


@Repository
public interface UserDao extends JpaRepository<User, String> {
}