package com.eduminication.data

data class CourseResource(
     var name: String,
     val author:User,
     var preview: String,
     var url: String
): BmobData()
