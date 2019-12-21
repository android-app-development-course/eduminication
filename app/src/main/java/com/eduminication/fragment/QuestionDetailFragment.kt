package com.eduminication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eduminication.dao.QuestionRepository
import com.eduminication.data.Question
import com.eduminication.databinding.FragmentQuestionDetailBinding

class QuestionDetailFragment : Fragment() {
    private val questionRepository = QuestionRepository()
    private lateinit var question: Question

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentQuestionDetailBinding.inflate(inflater,container,false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        question.title = "问题一"
        question.content = "这是一个测试问题"
    }
}
