package com.eduminication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.eduminication.adapter.QuestionRecylerViewAdapter
import com.eduminication.databinding.FragmentQuestionListBinding
import com.eduminication.viewmodel.QuestionListViewModel
import kotlinx.android.synthetic.main.fragment_question_list.*
import kotlinx.coroutines.launch

class QuestionListFragment : Fragment() {
    private val questionListViewModel = QuestionListViewModel()
    private val argsLazy by navArgs<QuestionListFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentQuestionListBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questions_recyler_view.adapter = QuestionRecylerViewAdapter()
            .apply {
                questionListViewModel.let {
                    it.questionList.observe(
                        viewLifecycleOwner,
                        Observer(this::submitList)
                    )
                }
            }

        add_floating_button.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(
                    QuestionListFragmentDirections.actionQuestionListFragmentToAddQuestionFragment(argsLazy.objectId)
                )
        }
    }

    override fun onResume() {
        super.onResume()
        questionListViewModel.questionList.value = null
        lifecycleScope.launch { questionListViewModel.refreshData(argsLazy.objectId) }
    }
}
