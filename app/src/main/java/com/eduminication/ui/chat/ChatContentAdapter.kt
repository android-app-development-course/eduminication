package com.eduminication.ui.chat

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eduminication.databinding.ChatContentItemBinding

class ChatContentAdapter :
    ListAdapter<ChatItemData, RecyclerView.ViewHolder>(ChatItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ChatContentViewHolder(
        private val binding: ChatContentItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chatItemData: ChatItemData) {
            binding.apply {
                chatItem = chatItemData
                //TODO replace the following by sql search
                //senderAvatar=getAvatar(chatItemData.Sender)
                executePendingBindings()
            }
        }
    }
}

private class ChatItemDiffCallback : DiffUtil.ItemCallback<ChatItemData>() {

    override fun areItemsTheSame(oldItem: ChatItemData, newItem: ChatItemData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ChatItemData, newItem: ChatItemData): Boolean {
        return oldItem.Date == newItem.Date &&
                oldItem.Content == newItem.Content &&
                oldItem.Sender == newItem.Sender
    }
}
