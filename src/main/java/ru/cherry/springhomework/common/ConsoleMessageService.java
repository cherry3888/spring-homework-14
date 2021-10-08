package ru.cherry.springhomework.common;

import org.springframework.stereotype.Service;
import ru.cherry.springhomework.common.MessageService;

import java.util.Scanner;

@Service
public class ConsoleMessageService implements MessageService {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String getMessage() {
        return scanner.nextLine();
    }

    @Override
    public Long getLongMessage() {
        Long id = scanner.nextLong();
        scanner.nextLine();
        return id;
    }
}
