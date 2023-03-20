package org.haknet.bookkeeper;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.haknet.bookkeeper.db.BookRoomDatabase;
import org.haknet.bookkeeper.db.dao.BookDao;
import org.haknet.bookkeeper.db.entity.BookEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookViewModel extends AndroidViewModel {
    private final LiveData<List<BookEntity>> allBooks;
    private final BookDao bookDao;

    public BookViewModel(@NonNull Application application) {
        super(application);
        BookRoomDatabase roomDatabase = BookRoomDatabase.getDatabase(application);
        this.bookDao = roomDatabase.bookDao();
        this.allBooks = this.bookDao.getAllBooks();
    }

    public void insert(BookEntity bookEntity) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> bookDao.insert(bookEntity));
    }

    public LiveData<List<BookEntity>> getAllBooks() {
        return this.allBooks;
    }
}
