package ru.cherry.springhomework.h2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cherry.springhomework.h2.domain.Author;

import java.util.Optional;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
