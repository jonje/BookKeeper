package org.haknet.bookkeeper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.haknet.bookkeeper.databinding.FragmentFirstBinding;
import org.haknet.bookkeeper.db.entity.BookEntity;

public class FirstFragment extends Fragment implements BookListAdapter.OnDeleteClickListener {

    private FragmentFirstBinding binding;
    private BookViewModel bookViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        this.bookViewModel = viewModelProvider.get(BookViewModel.class);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BookListAdapter bookListAdapter = new BookListAdapter(this.getContext(), this);
        RecyclerView recyclerView = binding.bookList;
        recyclerView.setAdapter(bookListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        this.bookViewModel.getAllBooks().observe(this.getViewLifecycleOwner(), bookListAdapter::setBooks);

        binding.fab.setOnClickListener(view1 -> {
            FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(null);
            Navigation.findNavController(view).navigate(action);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClickListener(BookEntity book) {
        this.bookViewModel.deleteBook(book);

    }
}