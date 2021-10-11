package ru.cherry.springhomework.mongo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cherry.springhomework.mongo.dao.BookDocumentRepository;
import ru.cherry.springhomework.mongo.dao.CommentDocumentRepository;
import ru.cherry.springhomework.mongo.domain.BookDocument;
import ru.cherry.springhomework.mongo.domain.CommentDocument;

import java.util.List;
import java.util.Optional;

@Service
public class CommentDocumentServiceImpl implements CommentDocumentService {
    private final CommentDocumentRepository commentDocumentRepository;
    private final BookDocumentRepository bookDocumentRepository;

    public CommentDocumentServiceImpl(CommentDocumentRepository commentDocumentRepository, BookDocumentRepository bookDocumentRepository) {
        this.commentDocumentRepository = commentDocumentRepository;
        this.bookDocumentRepository = bookDocumentRepository;
    }

    @Transactional
    @Override
    public CommentDocument save(String bookId, String content) {
        Optional<BookDocument> bookO = bookDocumentRepository.findById(bookId);
        if (bookO.isPresent()) {
            CommentDocument commentDocument = new CommentDocument(bookO.get(), content);
            return commentDocumentRepository.save(commentDocument);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommentDocument> getByBookId(String id) {
        return commentDocumentRepository.findCommentDocumentByBookDocumentId(id);
    }

}
