package com.eduminication.data

import android.net.Uri

data class CourseResource(
     var name: String,
     val author:User,
     var preview: String,
     var courseFileUri: Uri,
     var courseType: Int
): BmobData()
