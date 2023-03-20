package org.haknet.bookkeeper.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BookEntity {
    @NonNull
    @PrimaryKey
    private String id;
    private String author;
    private String book;

    public BookEntity(@NonNull String id, String author, String book) {
        this.id = id;
        this.author = author;
        this.book = book;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }
}
