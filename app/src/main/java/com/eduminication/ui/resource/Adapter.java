package com.eduminication.ui.resource;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.eduminication.R;
import com.eduminication.databinding.ResourceItemBinding;

class ItemDiffCallback extends DiffUtil.ItemCallback<Item> {

    @Override
    public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
        return oldItem.equals(newItem);
    }
}


public class Adapter extends ListAdapter<Item, Adapter.ItemHolder> {
    Adapter(@NonNull DiffUtil.ItemCallback diffCallback) {
        super(diffCallback);
    }

    @Override
    public Adapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(
                (ResourceItemBinding) DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.resource_item, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private ResourceItemBinding binding;

        ItemHolder(@NonNull ResourceItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            //TODO set name click event
            //binding.name.setOnClickListener
            binding.resources1.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_nav_resource_to_data));
            binding.resources2.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_nav_resource_to_data));
            binding.resources3.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_nav_resource_to_data));
            binding.resources4.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_nav_resource_to_data));
            binding.resources5.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_nav_resource_to_data));
        }

        void bind(Item item) {
            binding.setResourceItem(item);
        }
    }
}