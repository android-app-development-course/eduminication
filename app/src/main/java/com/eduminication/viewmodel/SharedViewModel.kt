package com.eduminication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eduminication.data.User

class SharedViewModel :ViewModel() {
    var user = MutableLiveData<User>()
}