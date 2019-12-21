package com.eduminication.data

data class ChatContent(val text: String) : BmobData()

data class ChatRecord(val Sender: User, val Content: ChatContent) : BmobData()
