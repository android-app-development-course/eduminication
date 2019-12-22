package com.eduminication.data

import cn.bmob.v3.datatype.BmobFile

data class CourseResource(
     var name: String,
     val author:User,
     var preview: String,
     var courseFile: BmobFile
): BmobData()
