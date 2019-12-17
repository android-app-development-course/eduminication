package com.eduminication.ui.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eduminication.databinding.FragmentQaBinding
import kotlinx.android.synthetic.main.fragment_qa.*

class QAFragment : Fragment() {
    private lateinit var binding: FragmentQaBinding
    private var titles: Array<String> = arrayOf("问题", "回答")
    private var mFragment: ArrayList<Fragment> = ArrayList<Fragment>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFragment.add(QuestionFragment())
        mFragment.add(AnswerFragment())
        tab.setViewPager(vp, titles, activity, mFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQaBinding.inflate(inflater, container, false)
        return binding.root
    }

}
