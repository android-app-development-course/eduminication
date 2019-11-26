package com.eduminication.ui.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.eduminication.R;

public class notificationFragment extends Fragment {

    private notificationViewModel notificationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        notificationViewModel =
                ViewModelProviders.of(this).get(notificationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notification, container, false);
        return root;
    }
}
