package com.eduminication.ui.questionList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eduminication.databinding.QuestionListItemBinding


private class QuestionDiffCallback : DiffUtil.ItemCallback<Question>() {
    override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
        return oldItem.id == newItem.id
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
                navigateToDiary(binding.question!!, itemView)
            }
        }
    }

    private fun navigateToDiary(
        diary: Question,
        it: View
    ) {
        /*
        TODO("add your navigation here")
        it.findNavController().navigate(
            DiaryListFragmentDirections.actionDiaryListFragmentToDiaryDetailFragment(diary.id)
        )
        */
    }

    fun bind(item: Question, onLongClickListener: (Long) -> Boolean) {
        binding.run {
            question = item
            questionCardView.setOnLongClickListener { onLongClickListener(item.id) }
            executePendingBindings()
        }
    }
}

class QuestionRecylerViewAdapter(private val onLongClickListener: (Long) -> Boolean) :
    ListAdapter<Question, QuestionViewHolder>(QuestionDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(
            QuestionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(getItem(position), onLongClickListener)
    }
}
