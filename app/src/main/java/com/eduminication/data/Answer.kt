package com.eduminication.data

data class Answer(
    var userId: String,
    var content: String,
    var questionId: String
) : BmobData()
