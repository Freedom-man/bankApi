package ru.mavrin.bankapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mavrin.bankapi.dao.CardDao;
import ru.mavrin.bankapi.dto.CardDto;
import ru.mavrin.bankapi.model.Card;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardService {
    private final CardDao cardDao;

    public Card create(CardDto dto) {
        Card card = Card.builder()
                .withUserId(dto.getUserId())
                .withAccountId(dto.getAccountId())
                .build();
        return cardDao.save(card);
    }

    public Optional<Card> findById(String id) {
        return cardDao.findById(id);
    }

    public void delete(String id) {
        cardDao.deleteById(id);
    }
    public List<Card> findAllByUserId(String userId) {
        return cardDao.findAllByUserId(userId);
    }
}