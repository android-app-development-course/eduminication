package com.eduminication.data

import java.util.*

data class Question(
    var title: String = "",
    var content: String = "",
    var date: Date = Date()
) {
    var id = totalId++

    companion object {
        public var totalId: Long = 0
            private set
    }
}
