package ru.cherry.springhomework.mongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cherry.springhomework.mongo.dao.BookDocumentRepository;
import ru.cherry.springhomework.mongo.domain.BookDocument;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookDocumentServiceImpl implements BookDocumentService {
    private final BookDocumentRepository bookDocumentRepository;

    @Override
    public List<BookDocument> getAllBooks() {
        return bookDocumentRepository.findAll();
    }

}
