package ru.cherry.springhomework.mongo.service;

import ru.cherry.springhomework.mongo.domain.BookDocument;

import java.util.List;

public interface BookDocumentService {
    List<BookDocument> getAllBooks();
}
