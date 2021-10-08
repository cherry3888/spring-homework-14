package ru.cherry.springhomework.h2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cherry.springhomework.h2.dao.BookRepository;
import ru.cherry.springhomework.h2.dao.CommentRepository;
import ru.cherry.springhomework.h2.domain.Book;
import ru.cherry.springhomework.h2.domain.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getByBookId(Long id) {
        Optional<Book> bookO = bookRepository.findById(id);
        List<Comment> comments = new ArrayList<>();
        if (bookO.isPresent()) {
            comments = bookO.get().getComments();
        }
        return comments;
    }

}
