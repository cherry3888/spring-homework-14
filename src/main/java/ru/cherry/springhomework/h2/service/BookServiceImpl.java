package ru.cherry.springhomework.h2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cherry.springhomework.h2.dao.BookRepository;
import ru.cherry.springhomework.h2.domain.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
