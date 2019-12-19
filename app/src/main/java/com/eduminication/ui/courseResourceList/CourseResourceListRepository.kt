package com.eduminication.ui.courseResourceList

class CourseResourceListRepository {
    fun getCourseResourceList(): List<CourseResource>{
        return listOf(CourseResource("课件一", "这是一个测试课件","http://www.inkwelleditorial.com/pdfSample.pdf"))
    }
}