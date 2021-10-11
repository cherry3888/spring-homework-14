package ru.cherry.springhomework.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDocument {
    @Id
    private String id;
    private String content;
    @DBRef
    private BookDocument bookDocument;

    public CommentDocument(BookDocument bookDocument, String content) {
        this.bookDocument = bookDocument;
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentDocument{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
