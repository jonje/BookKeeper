package org.haknet.bookkeeper.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import org.haknet.bookkeeper.db.entity.BookEntity;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insert(BookEntity bookEntity);

    @Query("select * from BookEntity")
    LiveData<List<BookEntity>> getAllBooks();
}
