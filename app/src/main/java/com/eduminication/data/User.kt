package com.eduminication.data

import cn.bmob.v3.BmobUser

enum class UserType{
    Teacher,
    Student
}

data class User (
    val userType:UserType
):BmobUser() {
    fun isTeacher() = userType == UserType.Teacher
    fun isStudent() = userType == UserType.Student
}
