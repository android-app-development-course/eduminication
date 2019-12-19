package com.eduminication.data

import java.util.*

data class ChatContent(val text: String)

data class ChatRecord(val Date: Date, val Sender: String, val Content: ChatContent)
