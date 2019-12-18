package com.eduminication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eduminication.databinding.FragmentQuestionDetailBinding
import com.eduminication.ui.questionList.Question

class QuestionDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentQuestionDetailBinding.inflate(inflater, container, false).apply {
            question = Question("问题一", "这是一个测试问题")
        }.root
    }
}
