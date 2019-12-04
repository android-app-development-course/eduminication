package com.eduminication.ui.courseResource;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.eduminication.databinding.FragmentResourceBinding;

import java.util.Objects;

public class ResourceFragment extends Fragment {
    private FragmentResourceBinding binding;
    private ResourceViewModel resourceViewModel = new ResourceViewModel();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentResourceBinding.inflate(inflater, container, false);

        final Adapter adapter = new Adapter(new ItemDiffCallback());
        resourceViewModel.getData().observe(getViewLifecycleOwner(), adapter::submitList);
        binding.resourceView.setAdapter(adapter);

        String url = "http://www.inkwelleditorial.com/pdfSample.pdf";
        for (Item item : Objects.requireNonNull(resourceViewModel.getData().getValue()))
            for (Resource resource : item.getResources())
                resource.setUrl(url.toString());
        adapter.notifyDataSetChanged();
        return binding.getRoot();
    }
}
