package ru.cherry.springhomework.h2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cherry.springhomework.h2.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
