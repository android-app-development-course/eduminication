package com.eduminication.dao

import com.eduminication.data.CourseResource

class CourseResourceListRepository {
    fun getCourseResourceList(): List<CourseResource>{
        return listOf(
            CourseResource(
                "课件一",
                "这是一个测试课件",
                "http://www.inkwelleditorial.com/pdfSample.pdf"
            )
        )
    }
}
