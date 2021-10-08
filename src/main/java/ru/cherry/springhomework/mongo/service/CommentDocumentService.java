package ru.cherry.springhomework.mongo.service;

import ru.cherry.springhomework.mongo.domain.CommentDocument;

import java.util.List;

public interface CommentDocumentService {
    CommentDocument save(String bookId, String content);

    List<CommentDocument> getByBookId(String id);
}
