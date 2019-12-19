package com.eduminication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eduminication.R
import com.eduminication.data.ChatRecord
import com.eduminication.databinding.ChatContentItemBinding

class ChatContentAdapter :
    ListAdapter<ChatRecord, ChatContentAdapter.ChatContentViewHolder>(
        ChatItemDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatContentViewHolder {
        return ChatContentViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.chat_content_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ChatContentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ChatContentViewHolder(
        private val binding: ChatContentItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chatRecordItemData: ChatRecord) {
            with(binding) {
                chatRecordItem = chatRecordItemData
                executePendingBindings()
            }
        }
    }
}

private class ChatItemDiffCallback : DiffUtil.ItemCallback<ChatRecord>() {

    override fun areItemsTheSame(oldItem: ChatRecord, newItem: ChatRecord): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ChatRecord, newItem: ChatRecord): Boolean {
        return oldItem.Date == newItem.Date &&
                oldItem.Content == newItem.Content &&
                oldItem.Sender == newItem.Sender
    }
}
