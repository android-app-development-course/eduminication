package com.eduminication.ui.courseResource

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eduminication.R
import com.eduminication.databinding.ResourceItemBinding

internal class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}

class Adapter internal constructor(diffCallback: DiffUtil.ItemCallback<Item>) :
    ListAdapter<Item, Adapter.ItemHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ItemHolder {
        return ItemHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.resource_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemHolder(private val binding: ResourceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            //TODO set name click event
            //binding.name.setOnClickListener

            //TODO set text view content description to required uri for testing
            val listener = View.OnClickListener {
                Navigation.findNavController(binding.root).navigate(
                    ResourceFragmentDirections.actionNavResourceToData((it as TextView).contentDescription.toString())
                )
            }
            binding.resources1.setOnClickListener(listener)
            binding.resources2.setOnClickListener(listener)
            binding.resources3.setOnClickListener(listener)
            binding.resources4.setOnClickListener(listener)
            binding.resources5.setOnClickListener(listener)
        }

        fun bind(item: Item) {
            binding.resourceItem = item
        }
    }
}
