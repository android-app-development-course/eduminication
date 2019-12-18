package com.eduminication.ui.questionList

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.eduminication.databinding.FragmentQuestionListBinding
import kotlinx.android.synthetic.main.fragment_question_list.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class QuestionListFragment : Fragment() {
    /*
        private val questionListViewModel by viewModels<QuestionListViewModel> {
            QuestionListViewModelFactory()
        }
    */
    private val questionListViewModel = QuestionListViewModel()

    private var refreshJob: Job? = null

    override fun onResume() {
        super.onResume()
        questionListViewModel.questionList.value = null
        refreshJob = lifecycleScope.launch {
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

        questions_recyler_view.adapter = QuestionRecylerViewAdapter { diaryId ->
            AlertDialog.Builder(context).run {
                setTitle("Delete a diary")
                setMessage("Are you sure to delete this diary?")
                setPositiveButton("Yes") { _, _ ->
                    questionListViewModel.delete(questionListViewModel.questionList.value!!.indexOfFirst {
                        it.id == diaryId
                    })
                }
                setNegativeButton("No", null)
                create()
            }.show()
            true
        }.apply {
            questionListViewModel.let {
                it.questionList.observe(
                    viewLifecycleOwner,
                    Observer { list -> submitList(list) })
                it.onDeleteListener = this::notifyItemRemoved
                it.onAddListener = { _ ->
                    add_floating_button.show()
                    notifyItemInserted(it.questionList.value!!.lastIndex)
                    findNavController().navigate(
                        QuestionListFragmentDirections.actionQuestionListFragmentToAddQuestionFragment()
                    )
                }
                it.onUpdateListener = { i: Int, _: Question -> notifyItemChanged(i) }
            }
        }
        add_floating_button.setOnClickListener {
            questionListViewModel.add(Question())
        }
    }
}
