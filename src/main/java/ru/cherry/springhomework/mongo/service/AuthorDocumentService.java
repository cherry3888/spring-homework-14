package ru.cherry.springhomework.mongo.service;

import ru.cherry.springhomework.mongo.domain.AuthorDocument;

import java.util.List;

public interface AuthorDocumentService {
    List<AuthorDocument> getAllAuthors();
}
