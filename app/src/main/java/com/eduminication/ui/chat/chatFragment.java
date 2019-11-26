package com.eduminication.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.eduminication.R;

public class chatFragment extends Fragment {

    private chatViewModel chatViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        chatViewModel =
                ViewModelProviders.of(this).get(chatViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chat, container, false);
        return root;
    }
}
