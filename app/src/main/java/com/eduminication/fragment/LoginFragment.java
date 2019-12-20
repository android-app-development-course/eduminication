package com.eduminication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.eduminication.R;
import com.eduminication.viewmodel.userViewModel;

public class LoginFragment extends Fragment {

    private userViewModel notificationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        notificationViewModel =
                ViewModelProviders.of(this).get(userViewModel.class);
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        return root;
    }
}
