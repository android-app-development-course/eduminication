package com.eduminication.data

import java.io.File

data class CourseResource(
     var name: String,
     val author:User,
     var preview: String,
     var courseFile: File,
     var courseType: Int
): BmobData()
