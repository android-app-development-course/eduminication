package com.eduminication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eduminication.databinding.FragmentAnswerDetailBinding
import com.eduminication.ui.data.QuestionAnswerListFragmentDirections
import kotlinx.android.synthetic.main.fragment_answer_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AnswerDetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AnswerDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnswerDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentAnswerDetailBinding.inflate(inflater, container, false).apply {
            answer = "这是一个测试回答"
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_answer_floating_button.setOnClickListener {
            AnswerDetailFragmentDirections.actionAnswerDetailFragmentToAddAnswerFragment()
        }
    }
}
