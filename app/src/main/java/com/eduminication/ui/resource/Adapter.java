package com.eduminication.ui.resource;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.eduminication.R;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private List<String> mDatas;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View root;
        TextView name;

        public ViewHolder(View view){
            super(view);
            root = view;
            name = view.findViewById(R.id.name);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resource_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.root.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               int position = holder.getAdapterPosition();
               String name = mDatas.get(position);
               Toast.makeText(view.getContext(), "你点击了View" + name, Toast.LENGTH_SHORT).show();
           }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        String mName = mDatas.get(position);
        holder.name.setText(mName);
    }

    @Override
    public int getItemCount(){
        return mDatas.size();
    }

    public Adapter(List<String> mDatas){
        this.mDatas = mDatas;
    }
}
