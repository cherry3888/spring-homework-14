package ru.cherry.springhomework.common;

public interface MessageService {
    void sendMessage(String message);

    String getMessage();

    Long getLongMessage();
}
