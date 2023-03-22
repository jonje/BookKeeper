package org.haknet.bookkeeper;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import org.haknet.bookkeeper.databinding.FragmentSecondBinding;
import org.haknet.bookkeeper.db.entity.BookEntity;

import java.util.UUID;

public class SecondFragment extends Fragment {
    public static final String TAG = SecondFragment.class.getSimpleName();
    private FragmentSecondBinding binding;
    private BookViewModel bookViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        this.bookViewModel = viewModelProvider.get(BookViewModel.class);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.bookSaveButton.setOnClickListener(v -> {
            String id = UUID.randomUUID().toString();
            String authorName = binding.authorName.getText().toString();
            String bookName = binding.bookName.getText().toString();

            BookEntity bookEntity = new BookEntity(id, authorName, bookName);
            Log.i(TAG, "Inserting book into database");
            bookViewModel.insert(bookEntity);

            NavHostFragment.findNavController(this).popBackStack();

        });

        binding.cancelButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).popBackStack();

        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}