package com.eduminication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.eduminication.databinding.FragmentAnswerDetailBinding
import com.eduminication.ui.data.QuestionAnswerListFragmentDirections
import kotlinx.android.synthetic.main.fragment_answer_detail.*

class AnswerDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentAnswerDetailBinding.inflate(inflater, container, false).apply {
            answer = "这是一个测试回答"
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_answer_floating_button.setOnClickListener {
            findNavController().navigate(
                QuestionAnswerListFragmentDirections.actionQuestionAnswerListFragmentToAddAnswerFragment()
            )
        }
    }
}
