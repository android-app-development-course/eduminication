package com.eduminication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eduminication.data.Answer
import com.eduminication.databinding.AnswerListItemBinding
import com.eduminication.fragment.QuestionAnswerListFragmentDirections


private class AnswerDiffCallback : DiffUtil.ItemCallback<Answer>() {
    override fun areItemsTheSame(oldItem: Answer, newItem: Answer): Boolean {
        return oldItem.objectId == newItem.objectId
    }

    override fun areContentsTheSame(oldItem: Answer, newItem: Answer): Boolean {
        return oldItem == newItem
    }
}

class AnswerViewHolder(
    private val binding: AnswerListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.answerCardView.run {
            setOnClickListener {
                navigateToAnswer(binding.answer!!, itemView)
            }
        }
    }

    private fun navigateToAnswer(
        Answer: Answer,
        it: View
    ) {
        it.findNavController().navigate(
            QuestionAnswerListFragmentDirections.actionQuestionAnswerListFragmentToAnswerDetailFragment(
                Answer.objectId
            )
        )
    }

    fun bind(item: Answer) {
        binding.run {
            answer = item
            executePendingBindings()
        }
    }
}

class AnswerRecylerViewAdapter :
    ListAdapter<Answer, AnswerViewHolder>(AnswerDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(
            AnswerListItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
