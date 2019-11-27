package com.eduminication.ui.resource;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.eduminication.R;
import java.util.List;

public class Adapter extends RecyclerView.Adapter implements View.OnClickListener {
    private List<Item> dataList;
    private Context context;

    public Adapter(Context context, List<Item> list) {
        this.context = context;
        dataList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resource_item, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Item item = dataList.get(position);
        ((ItemHolder) holder).name.setText(item.getName());
        ItemHolder itemHolder = (ItemHolder) holder;
        itemHolder.itemView.setTag(position);
        itemHolder.course1.setTag(position);
        itemHolder.course2.setTag(position);
        itemHolder.course3.setTag(position);
        itemHolder.course4.setTag(position);
        itemHolder.course5.setTag(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView course1, course2, course3, course4, course5;

        public ItemHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            course1 = itemView.findViewById(R.id.course1);
            course2 = itemView.findViewById(R.id.course2);
            course3 = itemView.findViewById(R.id.course3);
            course4 = itemView.findViewById(R.id.course4);
            course5 = itemView.findViewById(R.id.course5);
            //将创建的View注册点击事件
            itemView.setOnClickListener(Adapter.this);
            course1.setOnClickListener(Adapter.this);
            course2.setOnClickListener(Adapter.this);
            course3.setOnClickListener(Adapter.this);
            course4.setOnClickListener(Adapter.this);
            course5.setOnClickListener(Adapter.this);
        }
    }


    ////////////////////////////以下为item点击处理///////////////////////////////

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /** item里面有多个控件可以点击 */
    public enum ViewName {
        ITEM,
        PRACTISE
    }

    public interface OnRecyclerViewItemClickListener {
        void onClick(View view, ViewName viewName, int position);
    }

    @Override
    public void onClick(View v) {
        //注意这里使用getTag方法获取数据
        int position = (int) v.getTag();
        if (mOnItemClickListener != null) {
            switch (v.getId()){
                case R.id.name:
                    mOnItemClickListener.onClick(v, ViewName.PRACTISE, position);
                    break;
                default:
                    mOnItemClickListener.onClick(v, ViewName.ITEM, position);
                    break;
            }
        }
    }
}