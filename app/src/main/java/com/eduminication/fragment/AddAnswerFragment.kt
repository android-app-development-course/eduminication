package com.eduminication.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.eduminication.MainActivity
import com.eduminication.data.Answer
import com.eduminication.databinding.FragmentAddAnswerBinding
import com.eduminication.viewmodel.AnswerViewModel
import kotlinx.android.synthetic.main.fragment_add_answer.*

class AddAnswerFragment : Fragment() {
    private val argsLazy by navArgs<AddAnswerFragmentArgs>()
    private val answerViewModel = AnswerViewModel()
    private val sharedViewModel by lazy {
        (activity as MainActivity).sharedViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentAddAnswerBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        answer_pull.setOnClickListener {
            sharedViewModel.user.value?.run {
                answerViewModel.add(
                    Answer(
                        objectId,
                        answer_detail.editText!!.text.toString(),
                        argsLazy.questionId
                    )
                )
            }
            Toast.makeText(context, "添加成功", Toast.LENGTH_LONG).show()
        }
    }
}
