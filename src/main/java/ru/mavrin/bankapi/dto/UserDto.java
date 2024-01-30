package ru.mavrin.bankapi.dto;

import lombok.Data;

@Data
public class UserDto {
    private String firstName;
    private String username;
    private String password;
}
