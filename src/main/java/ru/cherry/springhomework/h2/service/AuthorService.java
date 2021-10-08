package ru.cherry.springhomework.h2.service;

import ru.cherry.springhomework.h2.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
}
