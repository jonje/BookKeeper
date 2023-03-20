package org.haknet.bookkeeper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.haknet.bookkeeper.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

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

        BookListAdapter bookListAdapter = new BookListAdapter(this.getContext());
        RecyclerView recyclerView = binding.bookList;
        recyclerView.setAdapter(bookListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.bookViewModel.getAllBooks().observe(this.getViewLifecycleOwner(), bookEntities -> {
            bookListAdapter.setBooks(bookEntities);
        });
        binding.fab.setOnClickListener(view1 -> NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}