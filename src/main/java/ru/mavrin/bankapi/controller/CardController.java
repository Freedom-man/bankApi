package ru.mavrin.bankapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mavrin.bankapi.dto.CardDto;
import ru.mavrin.bankapi.model.Card;
import ru.mavrin.bankapi.service.CardService;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    @PostMapping
    public ResponseEntity<Card> create(@RequestBody CardDto dto) {
        try {
            Card createdCard = cardService.create(dto);
            return new ResponseEntity<>(createdCard, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findById(@PathVariable String id) {
        Optional<Card> cardOptional = cardService.findById(id);
        return cardOptional.map(card -> new ResponseEntity<>(card, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            cardService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}