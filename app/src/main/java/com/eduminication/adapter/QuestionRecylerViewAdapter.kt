package com.eduminication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eduminication.data.Question
import com.eduminication.databinding.QuestionListItemBinding
import com.eduminication.fragment.QuestionListFragmentDirections


private class QuestionDiffCallback : DiffUtil.ItemCallback<Question>() {
    override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
        return oldItem.objectId == newItem.objectId
    }

    override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
        return oldItem == newItem
    }
}

class QuestionViewHolder(
    private val binding: QuestionListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.questionCardView.run {
            setOnClickListener {
                navigateToQuestion(binding.question!!, itemView)
            }
        }
    }

    private fun navigateToQuestion(
        question: Question,
        it: View
    ) {
        it.findNavController().navigate(
            QuestionListFragmentDirections.actionQuestionListFragmentToQuestionAnswerListFragment(
                question.objectId
            )
        )
    }

    fun bind(item: Question) {
        binding.run {
            question = item
            executePendingBindings()
        }
    }
}

class QuestionRecylerViewAdapter :
    ListAdapter<Question, QuestionViewHolder>(QuestionDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(
            QuestionListItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
