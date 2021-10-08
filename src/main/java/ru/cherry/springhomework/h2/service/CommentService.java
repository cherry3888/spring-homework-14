package ru.cherry.springhomework.h2.service;

import ru.cherry.springhomework.h2.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getByBookId(Long id);
}
