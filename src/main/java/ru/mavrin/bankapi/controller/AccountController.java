package ru.mavrin.bankapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mavrin.bankapi.dto.AccountDto;
import ru.mavrin.bankapi.model.Account;
import ru.mavrin.bankapi.service.AccountService;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody AccountDto dto) {
        try {
            Account createdAccount = accountService.create(dto);
            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable String id) {
        Optional<Account> accountOptional = accountService.findById(id);
        return accountOptional.map(account -> new ResponseEntity<>(account, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/change-balance/{id}")
    public ResponseEntity<Account> changeBalance(@PathVariable String id, @RequestParam double amount) {
        try {
            Account updatedAccount = accountService.changeBalance(id, amount);
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            accountService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}