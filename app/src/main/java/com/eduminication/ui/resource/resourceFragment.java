package com.eduminication.ui.resource;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.eduminication.DataActivity;
import com.eduminication.R;

import java.util.ArrayList;
import java.util.List;

public class resourceFragment extends Fragment {
    private resourceViewModel resourceViewModel;
    private RecyclerView mRecyclerView;
    private TextView mTextView, mTextView2, mTextView3, mTextView4, mTextView5;
    private List<Item> mDatas;

    protected void initData()
    {
        mDatas = new ArrayList<Item>();
        Item item = new Item("数据库", "课程1","课程2","课程3","课程4","课程5");
        mDatas.add(item);
        item = new Item("编译原理", "课程1","课程2","课程3","课程4","课程5");
        mDatas.add(item);
        item = new Item("数字图像处理", "课程1","课程2","课程3","课程4","课程5");
        mDatas.add(item);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        resourceViewModel =
                ViewModelProviders.of(this).get(resourceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_resource, container, false);

        //初始化list
        initData();

        //渲染recyclerView
        mRecyclerView = root.findViewById(R.id.resource_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        Adapter adapter = new Adapter(this.getActivity(), mDatas);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new Adapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, Adapter.ViewName viewName, int position) {
                //在此处理点击事件即可，viewName可以区分是item还是内部控件
                Intent intent;
                switch (view.getId()){
                    case R.id.course1:
                        intent = new Intent(getActivity(), DataActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.course2:
                        intent = new Intent(getActivity(), DataActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.course3:
                        intent = new Intent(getActivity(), DataActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.course4:
                        intent = new Intent(getActivity(), DataActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.course5:
                        intent = new Intent(getActivity(), DataActivity.class);
                        startActivity(intent);
                        break;
                     default:
                         break;
                }
            }
        });

        return root;
    }
}