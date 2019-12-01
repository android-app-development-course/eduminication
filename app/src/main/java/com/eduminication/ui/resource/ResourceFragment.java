package com.eduminication.ui.resource;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.eduminication.databinding.FragmentResourceBinding;

import java.util.ArrayList;

public class ResourceFragment extends Fragment {
    private FragmentResourceBinding binding;
    private ResourceViewModel resourceViewModel = new ResourceViewModel();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentResourceBinding.inflate(inflater, container, false);

        final Adapter adapter = new Adapter(new ItemDiffCallback());
        resourceViewModel.getData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Item>>() {
            @Override
            public void onChanged(ArrayList<Item> items) {
                adapter.submitList(items);
            }
        });
        binding.resourceView.setAdapter(adapter);

        return binding.getRoot();
    }
}
