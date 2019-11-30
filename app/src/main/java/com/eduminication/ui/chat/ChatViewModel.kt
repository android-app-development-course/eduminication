package com.eduminication.ui.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class ChatViewModel(val Owner:String) : ViewModel() {

    val chatItemDatas = getData()

    private fun getData(): MutableLiveData<MutableList<ChatItemData>> {
        return MutableLiveData(
            mutableListOf(
                ChatItemData(Date(), "Mars", ChatContent("Hello Android")),
                ChatItemData(Date(), "BlurringShadow", ChatContent("Hello Kotlin"))
            )
        )
    }
}
