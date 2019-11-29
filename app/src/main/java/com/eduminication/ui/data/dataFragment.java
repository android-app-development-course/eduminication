package com.eduminication.ui.data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.eduminication.R;
import com.github.clans.fab.FloatingActionMenu;

public class dataFragment extends Fragment {
    private dataViewModel dataViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dataViewModel =
                ViewModelProviders.of(this).get(dataViewModel.class);
        View root = inflater.inflate(R.layout.fragment_data, container, false);

        FloatingActionMenu fab = (FloatingActionMenu)root.findViewById(R.id.fab);
        fab.setClosedOnTouchOutside(true);

        return root;
    }
}
