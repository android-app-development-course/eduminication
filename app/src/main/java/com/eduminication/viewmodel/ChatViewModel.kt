package com.eduminication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eduminication.data.ChatRecord

class ChatViewModel(val Owner:String) : ViewModel() {

    val chatItemDatas = getData()

    private fun getData(): MutableLiveData<MutableList<ChatRecord>> {
        return MutableLiveData(
            mutableListOf(
//                ChatRecord(
//                    Date(),
//                    "Mars",
//                    ChatContent("Hello Android")
//                ),
//                ChatRecord(
//                    Date(),
//                    "BlurringShadow",
//                    ChatContent("Hello Kotlin")
//                )
            )
        )
    }
}
