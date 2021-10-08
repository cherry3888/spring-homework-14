package ru.cherry.springhomework.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDocument {
    @Id
    private String id;
    private String title;
    private AuthorDocument authorDocument;
    private GenreDocument genreDocument;

    public BookDocument(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public BookDocument(String title, AuthorDocument authorDocument, GenreDocument genreDocument) {
        this.title = title;
        this.authorDocument = authorDocument;
        this.genreDocument = genreDocument;
    }

    @Override
    public String toString() {
        return "BookDocument{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorDocument=" + authorDocument +
                ", genreDocument=" + genreDocument +
                '}';
    }
}
