package com.eduminication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eduminication.dao.QuestionRepository
import com.eduminication.data.Question
import java.sql.SQLException

class QuestionListViewModel: ViewModel() {
    private val questionRepository = QuestionRepository()
    var questionList = MutableLiveData<MutableList<Question>>()

    fun refreshData(listener:(List<Question>)->Unit= { _ -> }) {
        questionRepository.getAll { list, exception ->
            if (exception != null || list == null)
                throw SQLException("Unable to get data: $exception")
            questionList.value = list
            listener(list)
        }
    }
}
