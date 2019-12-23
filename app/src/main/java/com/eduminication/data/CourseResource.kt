package com.eduminication.data

data class CourseResource(
     var name: String,
     val author:User,
     var preview: String,
     var courseFileUri: String,
     var courseType: Int
): BmobData()
