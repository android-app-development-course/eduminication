package com.eduminication.data

data class Question(
    var title: String = "",
    var content: String = ""
) : BmobData()