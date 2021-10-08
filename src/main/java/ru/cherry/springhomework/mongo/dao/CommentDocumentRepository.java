package ru.cherry.springhomework.mongo.dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.cherry.springhomework.mongo.domain.CommentDocument;

import java.util.List;

@Repository
public interface CommentDocumentRepository extends MongoRepository<CommentDocument, String> {
    List<CommentDocument> findCommentDocumentByBookDocumentId(String bookDocumentId);
}
