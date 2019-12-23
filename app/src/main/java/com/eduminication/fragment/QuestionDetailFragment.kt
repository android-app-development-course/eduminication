package com.eduminication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.eduminication.databinding.FragmentQuestionDetailBinding
import com.eduminication.viewmodel.QuestionListViewModel
import kotlinx.coroutines.launch

class QuestionDetailFragment() : Fragment() {
    private lateinit var binding: FragmentQuestionDetailBinding
    private val questionListViewModel = QuestionListViewModel()
    var questionId: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentQuestionDetailBinding.inflate(inflater, container, false).run {
        binding = this
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionListViewModel.questionList.observe(viewLifecycleOwner) {
            binding.run {
                question = it.first()
                executePendingBindings()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch { questionListViewModel.showData(questionId) }
    }
}
