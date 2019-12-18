package com.eduminication.ui.questionList

class QuestionRepository {
    fun getDiaries(): List<Question> {
        return listOf(Question("", ""), Question("", ""))
    }

    fun insert(question: Question): List<Long> {
        return listOf(question.id)
    }

    fun update(question: Question) {
        TODO("not implemented")
    }

    fun delete(id: Long) {
        TODO("not implemented")
    }
}
