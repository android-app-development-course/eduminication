package com.eduminication.ui.questionList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class QuestionListViewModel(private val questionRepository: QuestionRepository) : ViewModel() {
    var diaryList = MutableLiveData<MutableList<Question>>()

    var onAddListener: ((Question) -> Unit)? = null
    var onUpdateListener: ((Int, Question) -> Unit)? = null
    var onDeleteListener: ((Int) -> Unit)? = null

    constructor() : this(QuestionRepository())

    suspend fun refreshData() {
        diaryList.value =
            questionRepository.getDiaries().toMutableList().apply { sortBy { it.id } }
    }

    fun add(diary: Question) {
        diaryList.value!!.add(diary)
        viewModelScope.launch {
            diary.id = questionRepository.insert(diary)[0]
            onAddListener?.run { this(diary) }
        }
    }

    fun update(index: Int, diary: Question) {
        diaryList.value!![index] = diary
        viewModelScope.launch {
            questionRepository.update(diary)
            onUpdateListener?.run { this(index, diary) }
        }
    }

    fun delete(index: Int) {
        val id = diaryList.value!![index].id
        diaryList.value!!.removeAt(index)
        viewModelScope.launch {
            questionRepository.delete(id)
            onDeleteListener?.run { this(index) }
        }
    }
}
