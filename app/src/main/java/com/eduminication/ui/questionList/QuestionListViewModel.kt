package com.eduminication.ui.questionList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class QuestionListViewModel(private val questionRepository: QuestionRepository) : ViewModel() {
    var questionList = MutableLiveData<MutableList<Question>>()

    var onAddListener: ((Question) -> Unit)? = null
    var onUpdateListener: ((Int, Question) -> Unit)? = null
    var onDeleteListener: ((Int) -> Unit)? = null

    constructor() : this(QuestionRepository())

    suspend fun refreshData() {
        questionList.value =
            questionRepository.getQuestions().toMutableList().apply { sortBy { it.id } }
    }

    fun add(diary: Question) {
        questionList.value!!.add(diary)
        viewModelScope.launch {
            diary.id = questionRepository.insert(diary)[0]
            onAddListener?.run { this(diary) }
        }
    }

    fun update(index: Int, diary: Question) {
        questionList.value!![index] = diary
        viewModelScope.launch {
            questionRepository.update(diary)
            onUpdateListener?.run { this(index, diary) }
        }
    }

    fun delete(index: Int) {
        val id = questionList.value!![index].id
        questionList.value!!.removeAt(index)
        viewModelScope.launch {
            questionRepository.delete(id)
            onDeleteListener?.run { this(index) }
        }
    }
}
