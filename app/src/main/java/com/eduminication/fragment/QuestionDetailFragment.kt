package com.eduminication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.eduminication.databinding.FragmentQuestionDetailBinding
import com.eduminication.viewmodel.QuestionListViewModel
import kotlinx.coroutines.launch

class QuestionDetailFragment() : Fragment() {
    private val questionListViewModel = QuestionListViewModel()
    private val argsLazy by navArgs<QuestionDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentQuestionDetailBinding.inflate(inflater, container, false).root

    override fun onResume() {
        super.onResume()
        questionListViewModel.questionList.value = null
        lifecycleScope.launch { questionListViewModel.showData(argsLazy.questionId) }
    }
}
