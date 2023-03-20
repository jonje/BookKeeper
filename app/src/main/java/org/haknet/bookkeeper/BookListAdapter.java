package org.haknet.bookkeeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.haknet.bookkeeper.db.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookVIewHolder> {
    private final Context context;
    private List<BookEntity> books = new ArrayList<>();

    public BookListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BookListAdapter.BookVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new BookVIewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListAdapter.BookVIewHolder holder, int position) {
        BookEntity book = this.books.get(position);
        holder.setData(book.getAuthor(), book.getBook(), position);
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }

    public void setBooks(List<BookEntity> bookEntities) {
        this.books = bookEntities;
        notifyDataSetChanged();
    }

    public static class BookVIewHolder extends RecyclerView.ViewHolder {
        private int position;

        public BookVIewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setData(String author, String book, int position) {
            TextView authorView = itemView.findViewById(R.id.author);
            TextView bookView = itemView.findViewById(R.id.book);
            authorView.setText(author);
            bookView.setText(book);
            this.position = position;

        }
    }
}
