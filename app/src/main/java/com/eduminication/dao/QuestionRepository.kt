package com.eduminication.dao

import com.eduminication.data.Question

class QuestionRepository {
    fun getQuestions(): List<Question> {
        return listOf(Question("问题一", "这是一个测试问题"))
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
