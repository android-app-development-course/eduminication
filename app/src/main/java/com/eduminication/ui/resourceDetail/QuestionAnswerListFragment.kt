package com.eduminication.ui.resourceDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.eduminication.AnswerDetailFragment
import com.eduminication.QuestionDetailFragment
import com.eduminication.R
import com.eduminication.databinding.FragmentQuestionAnswerListBinding
import com.eduminication.utils.ViewPageFragmentInfo
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_question_answer_list.*

class QuestionAnswerListFragment : Fragment() {
    private lateinit var pages: Array<ViewPageFragmentInfo>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pages = arrayOf(
            ViewPageFragmentInfo(getString(R.string.question), QuestionDetailFragment::class),
            ViewPageFragmentInfo(getString(R.string.answer), AnswerDetailFragment::class)
        )

        view_pager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int) =
                pages[position].fragmentClass.java.newInstance()

            override fun getItemCount() = pages.count()
        }

        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = pages[position].title
        }.attach()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentQuestionAnswerListBinding.inflate(inflater, container, false).root
}
