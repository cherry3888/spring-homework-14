package ru.cherry.springhomework.mongo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cherry.springhomework.mongo.domain.GenreDocument;

import java.util.List;

@Repository
public interface GenreDocumentRepository extends MongoRepository<GenreDocument, String> {
    List<GenreDocument> findAll();
}
