package com.eduminication.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.eduminication.databinding.FragmentChatBinding
import java.util.*

//TODO Add constructor to initialize view model
class ChatFragment : Fragment() {
    private lateinit var chatViewModel: ChatViewModel
    private lateinit var binding: FragmentChatBinding
    private val args: ChatFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chatViewModel = ChatViewModel(args.user)
        binding = FragmentChatBinding.inflate(inflater, container, false)

        binding.sendButton.setOnClickListener {
            val list = chatViewModel.chatItemDatas.value!!
            list.add(
                ChatItemData(
                    Date(),
                    chatViewModel.Owner,
                    ChatContent(binding.inputContent.text.toString())
                )
            )
            chatViewModel.chatItemDatas.postValue(list)
            binding.inputContent.text?.clear()
            binding.chatContent.adapter!!.notifyDataSetChanged()
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
        return binding.root
    }
}
