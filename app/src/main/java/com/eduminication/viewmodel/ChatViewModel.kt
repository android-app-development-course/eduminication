package com.eduminication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eduminication.dao.ChatRecordRepository
import com.eduminication.data.ChatRecord
import java.sql.SQLException

class ChatViewModel : ViewModel() {
    val chatRecordRepository = ChatRecordRepository()
    val chatItemData = MutableLiveData(mutableListOf<ChatRecord>())

    fun refreshData() {
        chatRecordRepository.getAll { mutableList, bmobException ->
            if (bmobException != null || mutableList == null)
                throw SQLException("unable to get data: $bmobException")
            chatItemData.value = mutableList
        }
    }


}
