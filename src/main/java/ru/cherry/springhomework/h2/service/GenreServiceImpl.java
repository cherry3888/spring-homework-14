package ru.cherry.springhomework.h2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cherry.springhomework.h2.dao.GenreRepository;
import ru.cherry.springhomework.h2.domain.Genre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    final private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional
    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

}
