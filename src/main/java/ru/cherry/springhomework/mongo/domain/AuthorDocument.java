package ru.cherry.springhomework.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDocument {
    @Id
    private String id;
    private String name;

    public AuthorDocument(String name) {
        this.name = name;
    }
}
