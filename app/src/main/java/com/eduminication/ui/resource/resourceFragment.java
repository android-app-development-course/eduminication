package com.eduminication.ui.resource;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.eduminication.R;

import java.util.ArrayList;
import java.util.List;

public class resourceFragment extends Fragment {

    private resourceViewModel resourceViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<String> mDatas;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        resourceViewModel =
                ViewModelProviders.of(this).get(resourceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_resource, container, false);

        initData();
        mRecyclerView = root.findViewById(R.id.resource_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        Adapter adapter = new Adapter(mDatas);
        mRecyclerView.setAdapter(adapter);

        return root;
    }

    protected void initData()
    {
        mDatas = new ArrayList<String>();
        mDatas.add("数据库");
        mDatas.add("编译原理");
        mDatas.add("数字图像处理");
    }
}