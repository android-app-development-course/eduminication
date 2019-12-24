package com.eduminication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.eduminication.databinding.FragmentAnswerDetailBinding
import com.eduminication.viewmodel.AnswerViewModel
import kotlinx.coroutines.launch

class AnswerDetailFragment : Fragment() {
    private lateinit var binding: FragmentAnswerDetailBinding
    private val answerListViewModel = AnswerViewModel()
    private val argsLazy by navArgs<AnswerDetailFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAnswerDetailBinding.inflate(inflater, container, false).run {
        binding = this
        root
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            answerListViewModel.getById(argsLazy.questionId) { answer ->
                binding.run {
                    this.answer = answer
                    executePendingBindings()
                }
            }
        }
    }
}
