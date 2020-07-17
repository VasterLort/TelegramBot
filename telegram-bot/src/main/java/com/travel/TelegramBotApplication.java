package com.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class TelegramBotApplication {
    static {
        ApiContextInitializer.init();
    }

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotApplication.class, args);
    }
}
