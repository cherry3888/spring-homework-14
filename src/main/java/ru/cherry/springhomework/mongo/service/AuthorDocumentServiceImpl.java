package ru.cherry.springhomework.mongo.service;

import org.springframework.stereotype.Service;
import ru.cherry.springhomework.mongo.dao.AuthorDocumentRepository;
import ru.cherry.springhomework.mongo.domain.AuthorDocument;

import java.util.List;

@Service
public class AuthorDocumentServiceImpl implements AuthorDocumentService {
    final private AuthorDocumentRepository authorDocumentRepository;

    public AuthorDocumentServiceImpl(AuthorDocumentRepository authorDocumentRepository) {
        this.authorDocumentRepository = authorDocumentRepository;
    }

    @Override
    public List<AuthorDocument> getAllAuthors() {
        return authorDocumentRepository.findAll();
    }

}
