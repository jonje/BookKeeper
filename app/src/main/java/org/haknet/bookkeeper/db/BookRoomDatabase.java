package org.haknet.bookkeeper.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.haknet.bookkeeper.db.dao.BookDao;
import org.haknet.bookkeeper.db.entity.BookEntity;

@Database(entities = {BookEntity.class}, version = 1)
public abstract class BookRoomDatabase extends RoomDatabase {
    private static BookRoomDatabase bookRoomDatabaseInstance;
    public abstract BookDao bookDao();

    public static BookRoomDatabase getDatabase(Context context) {
        if (bookRoomDatabaseInstance == null) {
            synchronized (BookRoomDatabase.class) {
                if (bookRoomDatabaseInstance == null) {
                    bookRoomDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                            BookRoomDatabase.class, "book_database")
                            .build();
                }
            }
        }

        return bookRoomDatabaseInstance;
    }
}
