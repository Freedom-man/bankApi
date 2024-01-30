package ru.mavrin.bankapi.dto;

import lombok.Data;

@Data
public class CardDto {
    private String userId;
    private String accountId;
}