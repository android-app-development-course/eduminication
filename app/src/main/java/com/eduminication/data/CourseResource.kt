package com.eduminication.data

import java.util.*

data class CourseResource(
    var name: String,
    var preview: String,
    var url: String,
    var date: Date = Date()
) {
    var id = totalId++

    companion object {
        public var totalId: Long = 0
            private set
    }
}
