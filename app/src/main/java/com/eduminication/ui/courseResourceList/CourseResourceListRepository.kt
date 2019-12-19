package com.eduminication.ui.courseResourceList

import com.eduminication.data.CourseResource

class CourseResourceListRepository {
    fun getCourseResourceList(): List<CourseResource>{
        var courseResource = CourseResource()
        courseResource.name = "课件一"
        courseResource.preview = "这是一个测试课件"
        courseResource.url = "http://www.inkwelleditorial.com/pdfSample.pdf"
        return listOf(courseResource)
    }
}