package ru.cherry.springhomework.h2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cherry.springhomework.h2.dao.AuthorRepository;
import ru.cherry.springhomework.h2.domain.Author;

import java.util.List;


@Service
public class AuthorServiceImpl implements AuthorService {
    final private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

}
