package ru.cherry.springhomework.mongo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cherry.springhomework.mongo.dao.GenreDocumentRepository;
import ru.cherry.springhomework.mongo.domain.GenreDocument;

import java.util.List;
import java.util.Optional;

@Service
public class GenreDocumentServiceImpl implements GenreDocumentService {
    final private GenreDocumentRepository genreDocumentRepository;

    public GenreDocumentServiceImpl(GenreDocumentRepository genreDocumentRepository) {
        this.genreDocumentRepository = genreDocumentRepository;
    }

    @Transactional
    @Override
    public List<GenreDocument> getAllGenres() {
        return genreDocumentRepository.findAll();
    }

}
