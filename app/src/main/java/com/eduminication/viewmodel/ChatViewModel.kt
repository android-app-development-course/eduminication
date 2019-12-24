package com.eduminication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eduminication.dao.ChatRecordRepository
import com.eduminication.data.ChatRecord
import com.eduminication.data.User
import java.sql.SQLException

class ChatViewModel : ViewModel() {
    val chatRecordRepository = ChatRecordRepository()
    val chatItemData = MutableLiveData(mutableListOf<ChatRecord>())

    fun refreshData(user: User) {
        chatRecordRepository.getAll(user) { mutableList, bmobException ->
            if (bmobException != null || mutableList == null)
                throw SQLException("unable to get data: $bmobException")
            chatItemData.value = mutableList
        }
    }
}
