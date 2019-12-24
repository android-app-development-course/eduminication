package com.eduminication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eduminication.dao.AnswerRepository
import com.eduminication.data.Answer
import java.sql.SQLException

class AnswerListViewModel: ViewModel() {
    private val answerRepository = AnswerRepository()
    var answerList = MutableLiveData<MutableList<Answer>>()

    fun refreshData(objectId: String, listener:(List<Answer>)->Unit= { _ -> }) {
        answerRepository.getItemByQuestionId(objectId) { list, exception ->
            if (exception != null || list == null)
                throw SQLException("Unable to get data: $exception")
            answerList.value = list
            listener(list)
        }
    }
}
