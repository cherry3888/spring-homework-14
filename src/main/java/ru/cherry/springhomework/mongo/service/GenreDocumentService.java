package ru.cherry.springhomework.mongo.service;

import ru.cherry.springhomework.mongo.domain.GenreDocument;

import java.util.List;
import java.util.Optional;

public interface GenreDocumentService {
    List<GenreDocument> getAllGenres();
}
