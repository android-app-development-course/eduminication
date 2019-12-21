package com.eduminication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import cn.bmob.v3.BmobUser
import com.eduminication.adapter.ChatContentAdapter
import com.eduminication.data.ChatContent
import com.eduminication.data.ChatRecord
import com.eduminication.data.User
import com.eduminication.databinding.FragmentChatBinding
import com.eduminication.viewmodel.ChatViewModel
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment() {
    private val chatViewModel by lazy { ChatViewModel() }

    override fun onResume() {
        super.onResume()
        chatViewModel.refreshData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        send_button.setOnClickListener {
            val list = chatViewModel.chatItemData.value!!
            list.add(
                ChatRecord(
                    BmobUser.getCurrentUser(User::class.java),
                    ChatContent(input_content.text.toString())
                )
            )
            input_content.text?.clear()
            chat_content.adapter!!.notifyItemInserted(list.lastIndex)
        }

        ChatContentAdapter().let {
            chatViewModel.chatItemData.observe(
                viewLifecycleOwner,
                Observer(it::submitList)
            )
            chat_content.adapter = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentChatBinding.inflate(inflater, container, false).root
}
