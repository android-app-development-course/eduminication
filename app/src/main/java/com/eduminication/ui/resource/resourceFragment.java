package com.eduminication.ui.resource;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.eduminication.R;

public class resourceFragment extends Fragment {

    private resourceViewModel resourceViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        resourceViewModel =
                ViewModelProviders.of(this).get(resourceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_resource, container, false);
        return root;
    }
}
