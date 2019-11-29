package com.eduminication.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import com.eduminication.R

class ChatFragment : Fragment() {

    private lateinit var chatViewModel: ChatViewModel
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        chatViewModel = ViewModelProviders.of(this).get(ChatViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_chat, container, false)
        return root
    }
}
