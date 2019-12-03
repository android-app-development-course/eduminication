package com.eduminication.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.eduminication.databinding.FragmentChatBinding
import kotlinx.android.synthetic.main.fragment_chat.*
import java.util.*

//TODO Add constructor to initialize view model
class ChatFragment : Fragment() {
    private lateinit var chatViewModel: ChatViewModel
    private lateinit var binding: FragmentChatBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let {
            ChatFragmentArgs.fromBundle(savedInstanceState).run {
                chatViewModel = ChatViewModel(user)
            }
        }

        send_button.setOnClickListener {
            val list = chatViewModel.chatItemDatas.value!!
            list.add(
                ChatItemData(
                    Date(),
                    chatViewModel.Owner,
                    ChatContent(input_content.text.toString())
                )
            )
            chatViewModel.chatItemDatas.postValue(list)
            input_content.text?.clear()
            chat_content.adapter!!.notifyDataSetChanged()
        }

        context?.let {
            ChatContentAdapter().let {
                chatViewModel.chatItemDatas.observe(
                    viewLifecycleOwner,
                    Observer(it::submitList)
                )
                binding.chatContent.adapter = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }
}
