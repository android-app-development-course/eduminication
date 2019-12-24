package com.eduminication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.eduminication.adapter.AnswerRecylerViewAdapter
import com.eduminication.databinding.FragmentAnswerListBinding
import com.eduminication.viewmodel.AnswerListViewModel
import kotlinx.android.synthetic.main.fragment_answer_list.*
import kotlinx.coroutines.launch

class AnswerListFragment : Fragment() {
    private val answerListViewModel = AnswerListViewModel()
    var questionId: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAnswerListBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.adapter = AnswerRecylerViewAdapter()
            .apply {
                answerListViewModel.answerList.observe(
                    viewLifecycleOwner,
                    Observer(this::submitList)
                )
            }

        fab_add.setOnClickListener {
            findNavController().navigate(
                QuestionAnswerListFragmentDirections.actionQuestionAnswerListFragmentToAddAnswerFragment(questionId)
            )
        }
    }

    override fun onResume() {
        super.onResume()
        answerListViewModel.answerList.value = null
        lifecycleScope.launch { answerListViewModel.refreshData(questionId) }
    }
}
