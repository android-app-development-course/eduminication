package com.eduminication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.eduminication.databinding.FragmentQuestionListBinding
import com.eduminication.viewmodel.QuestionListViewModel
import kotlinx.coroutines.launch

class QuestionListFragment : Fragment() {
    /*
        private val questionListViewModel by viewModels<QuestionListViewModel> {
            QuestionListViewModelFactory()
        }
    */
    private val questionListViewModel = QuestionListViewModel()


    override fun onResume() {
        super.onResume()
        questionListViewModel.questionList.value = null
        lifecycleScope.launch {
            questionListViewModel.refreshData()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentQuestionListBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
