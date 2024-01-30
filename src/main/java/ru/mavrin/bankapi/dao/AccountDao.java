package ru.mavrin.bankapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mavrin.bankapi.model.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, String> {
}