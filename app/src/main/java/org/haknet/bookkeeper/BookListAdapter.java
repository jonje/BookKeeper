package org.haknet.bookkeeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import org.haknet.bookkeeper.db.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookVIewHolder> {
    private final Context context;
    private final OnDeleteClickListener onDeleteListener;
    private List<BookEntity> books = new ArrayList<>();

    public BookListAdapter(Context context, OnDeleteClickListener onDeleteClickListener) {
        this.context = context;
        this.onDeleteListener = onDeleteClickListener;
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
        holder.setData(book, position);
        holder.setListeners(this.onDeleteListener);
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }

    public void setBooks(List<BookEntity> bookEntities) {
        this.books = bookEntities;
        notifyDataSetChanged();
    }

    public interface OnDeleteClickListener {
        void onClickListener(BookEntity book);
    }

    static class BookVIewHolder extends RecyclerView.ViewHolder {
        private int position;
        private BookEntity book;

        public BookVIewHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void setData(BookEntity book, int position) {
            this.book = book;
            TextView authorView = itemView.findViewById(R.id.author);
            TextView bookView = itemView.findViewById(R.id.book);
            authorView.setText(book.getAuthor());
            bookView.setText(book.getBook());
            this.position = position;
        }

        public void setListeners(OnDeleteClickListener onDeleteClickListener) {
            ImageView editButton = this.itemView.findViewById(R.id.editButton);
            editButton.setOnClickListener(v -> {

                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action =
                        FirstFragmentDirections.actionFirstFragmentToSecondFragment(this.book.getId());
                Navigation.findNavController(itemView).navigate(action);

            });
            ImageView deleteButton = this.itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(v -> {
                onDeleteClickListener.onClickListener(this.book);
            });
        }
    }
}
