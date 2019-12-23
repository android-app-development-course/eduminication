package com.eduminication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.eduminication.databinding.FragmentAnswerDetailBinding
import com.eduminication.viewmodel.AnswerViewModel
import kotlinx.android.synthetic.main.fragment_answer_detail.*
import kotlinx.coroutines.launch

class AnswerDetailFragment : Fragment() {
    private lateinit var binding: FragmentAnswerDetailBinding
    private val answerListViewModel = AnswerViewModel()
    var questionId: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAnswerDetailBinding.inflate(inflater, container, false).run {
        binding = this
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        answerListViewModel.answerList.observe(viewLifecycleOwner){
            binding.run {
                answer = it.first()
                executePendingBindings()
            }
        }
        add_answer_floating_button.setOnClickListener {
            findNavController().navigate(
                QuestionAnswerListFragmentDirections.actionQuestionAnswerListFragmentToAddAnswerFragment(questionId)
            )
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch { answerListViewModel.showData(questionId) }
    }
}
