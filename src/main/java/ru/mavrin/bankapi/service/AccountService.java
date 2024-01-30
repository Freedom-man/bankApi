package ru.mavrin.bankapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mavrin.bankapi.dao.AccountDao;
import ru.mavrin.bankapi.dto.AccountDto;
import ru.mavrin.bankapi.model.Account;

import java.util.Optional;


@Service
@AllArgsConstructor
public class AccountService {
    private final AccountDao accountDao;

    public Account create(AccountDto dto) {
        Account account = Account.builder()
                .withUserId(dto.getUserId())
                .withBalance(0.0)
                .build();
        return accountDao.save(account);
    }

    public Optional<Account> findById(String id) {
        return accountDao.findById(id);
    }

    public Account changeBalance(String id, double amount) {
        Optional<Account> accountOptional = accountDao.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setBalance(account.getBalance() + amount);
            return accountDao.save(account);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    public void delete(String id) {
        accountDao.deleteById(id);
    }
}
