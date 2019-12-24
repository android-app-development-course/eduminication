package com.eduminication.data

data class ChatRecord(var sender: User, var content: String) : BmobData()
