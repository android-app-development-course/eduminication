package com.eduminication.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.eduminication.data.Question
import com.eduminication.databinding.FragmentAddQuestionBinding
import com.eduminication.viewmodel.QuestionListViewModel
import kotlinx.android.synthetic.main.fragment_add_question.*

class AddQuestionFragment : Fragment() {
    private val argsLazy by navArgs<AddQuestionFragmentArgs>()
    private val questionListViewModel = QuestionListViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAddQuestionBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_question.setOnClickListener {
            val question = Question(
                question_title.editText!!.text.toString(),
                question_detail.editText!!.text.toString(),
                argsLazy.objectId
            )
            questionListViewModel.add(question)
            Toast.makeText(context, "添加成功", Toast.LENGTH_LONG).show()
        }
    }
}
