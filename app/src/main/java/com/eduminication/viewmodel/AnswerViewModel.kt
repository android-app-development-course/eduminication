package com.eduminication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.bmob.v3.exception.BmobException
import com.eduminication.dao.AnswerRepository
import com.eduminication.data.Answer
import java.sql.SQLException

class AnswerViewModel : ViewModel() {
    private val answerRepository = AnswerRepository()
    var answerList = MutableLiveData<MutableList<Answer>>()

    fun refreshData(questionId: String, listener: (MutableList<Answer>) -> Unit = { _ -> }) {
        answerRepository.getItemByQuestionId(questionId) { list, exception ->
            if (exception != null || list == null)
                throw SQLException("Unable to get data: $exception")
            answerList.value = list
            listener(list)
        }
    }

    fun getById(id: String, listener: (Answer) -> Unit = { _ -> }) =
        answerRepository.getById(id) { answer, exception ->
            if (exception != null)
                throw SQLException("Unable to get data: $exception")
            listener(answer)
        }


    fun add(answer: Answer, listener: (String?, BmobException?) -> Unit = { _, _ -> }) {
        answerList.value?.add(answer)
        answerRepository.add(answer, listener)
    }
}
