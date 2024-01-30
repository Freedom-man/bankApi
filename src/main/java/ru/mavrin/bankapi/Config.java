package ru.mavrin.bankapi;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@Configuration
@ComponentScan(basePackages = "ru.mavrin.bankapi")
@EnableJpaRepositories(basePackages = "ru.mavrin.bankapi")
@EntityScan(basePackages = "ru.mavrin.bankapi")
public class Config {
}