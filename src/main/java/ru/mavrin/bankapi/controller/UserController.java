package ru.mavrin.bankapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mavrin.bankapi.dto.AccountDto;
import ru.mavrin.bankapi.dto.CardDto;
import ru.mavrin.bankapi.dto.UserDto;
import ru.mavrin.bankapi.model.Account;
import ru.mavrin.bankapi.model.Card;
import ru.mavrin.bankapi.model.User;
import ru.mavrin.bankapi.service.AccountService;
import ru.mavrin.bankapi.service.CardService;
import ru.mavrin.bankapi.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/users")

public class UserController {

    private final UserService userService;
    private final AccountService accountService;
    private final CardService cardService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDto dto) {
        try {
            User createdUser = userService.create(dto);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findByUserId(@PathVariable String id) {
        Optional<User> userOptional = userService.findByUserId(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody UserDto userDto) {
        try {
            Optional<User> userOptional = userService.findByUserId(id);
            if (userOptional.isPresent()) {
                User existingUser = userOptional.get();
                existingUser.setFirstName(userDto.getFirstName());
                existingUser.setUsername(userDto.getUsername());

                User updatedUser = userService.update(existingUser);
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/change-password/{id}")
    public ResponseEntity<User> changePassword(@PathVariable String id, @RequestBody UserDto userDto) {
        try {
            Optional<User> userOptional = userService.findByUserId(id);
            if (userOptional.isPresent()) {
                User existingUser = userOptional.get();

                existingUser.setPassword(userDto.getPassword());

                User updatedUser = userService.update(existingUser);
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/create-account/{id}")
    public ResponseEntity<Account> createAccount(@PathVariable String id) {
        try {
            AccountDto accountDto = new AccountDto();
            accountDto.setUserId(id);
            Account createdAccount = accountService.create(accountDto);
            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-card/{id}")
    public ResponseEntity<Card> createCard(@PathVariable String id, @RequestParam String accountId) {
        try {
            CardDto cardDto = new CardDto();
            cardDto.setUserId(id);
            cardDto.setAccountId(accountId);
            Card createdCard = cardService.create(cardDto);
            return new ResponseEntity<>(createdCard, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all-cards/{id}")
    public ResponseEntity<List<Card>> getAllCards(@PathVariable String id) {
        try {
            List<Card> cards = cardService.findAllByUserId(id);
            return new ResponseEntity<>(cards, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
