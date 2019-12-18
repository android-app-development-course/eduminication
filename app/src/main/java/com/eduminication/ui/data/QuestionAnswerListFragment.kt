package com.eduminication.ui.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eduminication.AnswerDetailFragment
import com.eduminication.QuestionDetailFragment
import com.eduminication.databinding.FragmentQuestionAnswerListBinding
import kotlinx.android.synthetic.main.fragment_question_answer_list.*

class QuestionAnswerListFragment : Fragment() {
    private lateinit var binding: FragmentQuestionAnswerListBinding
    private var titles: Array<String> = arrayOf("问题", "回答")
    private var mFragment: ArrayList<Fragment> = ArrayList<Fragment>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFragment.add(QuestionDetailFragment())
        mFragment.add(AnswerDetailFragment())
        tab.setViewPager(vp, titles, activity, mFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionAnswerListBinding.inflate(inflater, container, false)
        return binding.root
    }

}
