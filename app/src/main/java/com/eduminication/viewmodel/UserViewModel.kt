package com.eduminication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.bmob.v3.exception.BmobException
import com.eduminication.dao.UserRepository
import com.eduminication.data.User

class UserViewModel : ViewModel() {
    val user = MutableLiveData(User())
    private val userRepository = UserRepository()

    fun logIn( listener: (BmobException?) -> Unit)=userRepository.logIn(user.value!!,listener)

    fun register(listener: (String?, BmobException?) -> Unit)=userRepository.signIn(user.value!!,listener)
}