package com.eduminication.data

const val DataStructure=0
const val PrinciplesOfCompiler=1
const val LinearAlgebra=2

data class CourseResource(
     var name: String,
     val author:User,
     var preview: String,
     var courseFileUri: String,
     var courseType: Int
): BmobData()
