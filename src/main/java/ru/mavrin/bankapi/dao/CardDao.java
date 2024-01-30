package ru.mavrin.bankapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mavrin.bankapi.model.Card;

import java.util.List;

@Repository
public interface CardDao extends JpaRepository<Card, String> {
    List<Card> findAllByUserId(String userId);
}