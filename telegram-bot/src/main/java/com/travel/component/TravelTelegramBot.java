package com.travel.component;

import com.travel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@ConfigurationProperties(prefix = "telegrambot")
public class TravelTelegramBot extends TelegramLongPollingBot {
    private String botUserName;
    private String botToken;

    @Autowired
    private CityService cityService;

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            final long chat_id = update.getMessage().getChatId();
            String cityName = update.getMessage().getText();
            String cityInfo = cityService.getCityInfo(cityName);
            sendTextMessage(chat_id, cityName + ": " + cityInfo);
        }
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public String getBotUsername() {
        return botUserName;
    }

    public String getBotToken() {
        return botToken;
    }

    private synchronized void sendTextMessage(long chat_id, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false)
                .setChatId(chat_id)
                .setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
