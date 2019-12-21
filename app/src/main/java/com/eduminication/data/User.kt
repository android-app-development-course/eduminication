package com.eduminication.data

//to compatible with bmob data type
object UserType {
    const val Teacher = 1
    const val Student = 0
}

data class User(
    @JvmField
    var name:String = "",
    @JvmField
    var password:String = "",
    @JvmField
    var userType: Int = UserType.Teacher
) : BmobData() {

    fun isTeacher() = userType == UserType.Teacher
    fun isStudent() = userType == UserType.Student
}
