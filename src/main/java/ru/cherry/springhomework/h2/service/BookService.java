package ru.cherry.springhomework.h2.service;

import ru.cherry.springhomework.h2.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
}
