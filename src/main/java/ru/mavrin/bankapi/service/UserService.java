package ru.mavrin.bankapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mavrin.bankapi.dao.UserDao;
import ru.mavrin.bankapi.dto.UserDto;
import ru.mavrin.bankapi.model.User;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDao userDao;

    public User create(UserDto dto) {
        User user = User.builder()
                .withFirstName(dto.getFirstName())
                .withUsername(dto.getUsername())
                .withPassword(dto.getPassword())
                .build();
        return userDao.save(user);
    }

    public Optional<User> findByUserId(String id) {
        return userDao.findById(id);
    }

    public User update(User user) {
        return userDao.save(user);
    }

    public void delete(String id) {
        userDao.deleteById(id);
    }

    public List<User> findAllUsers() {
        return userDao.findAll();
    }
}
