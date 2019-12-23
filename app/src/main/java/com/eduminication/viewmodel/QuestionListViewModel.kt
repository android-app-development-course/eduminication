package com.eduminication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.bmob.v3.exception.BmobException
import com.eduminication.dao.QuestionRepository
import com.eduminication.data.Question
import java.sql.SQLException

class QuestionListViewModel: ViewModel() {
    private val questionRepository = QuestionRepository()
    var questionList = MutableLiveData<MutableList<Question>>()

    fun refreshData(objectId: String, listener:(List<Question>)->Unit= { _ -> }) {
        questionRepository.getAllByResourceId(objectId) { list, exception ->
            if (exception != null || list == null)
                throw SQLException("Unable to get data: $exception")
            questionList.value = list
            listener(list)
        }
    }

    fun showData(questionId: String, listener:(Question)->Unit= { _ -> }) {
        questionRepository.getItemByQuestionId(questionId) { question, exception ->
            if (exception != null || question == null)
                throw SQLException("Unable to get data: $exception")
            questionList.value = mutableListOf(question)
            listener(question)
        }
    }

    fun add (question: Question, listener: (String?, BmobException?) -> Unit = { _, _ -> }){
        questionList.value?.add(question)
        questionRepository.add(question, listener)
    }
}
