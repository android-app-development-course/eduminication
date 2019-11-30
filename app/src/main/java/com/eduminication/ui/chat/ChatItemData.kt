package com.eduminication.ui.chat

import java.util.*

data class ChatContent(val text: String)

data class ChatItemData(val Date: Date, val Sender: String, val Content: ChatContent)
