package com.eduminication.ui.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.eduminication.R;

public class messageFragment extends Fragment {

    private messageViewModel messageViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        messageViewModel =
                ViewModelProviders.of(this).get(messageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_message, container, false);
        return root;
    }
}
