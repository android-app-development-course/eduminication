package com.eduminication.ui.chat

import androidx.lifecycle.ViewModel
import java.util.*

class ChatViewModel : ViewModel() {

    private var chatItemDatas = getData()

    private fun getData(): MutableList<ChatItemData> {
        return mutableListOf(
            ChatItemData(Date(), "Mars", ChatContent("Hello Android")),
            ChatItemData(Date(), "BlurringShadow", ChatContent("Hello Kotlin"))
        )
    }
}
