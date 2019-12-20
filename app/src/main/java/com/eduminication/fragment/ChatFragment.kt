package com.eduminication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.eduminication.adapter.ChatContentAdapter
import com.eduminication.databinding.FragmentChatBinding
import com.eduminication.viewmodel.ChatViewModel
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment() {
    private val args: ChatFragmentArgs by navArgs()
    private val chatViewModel by lazy { ChatViewModel(args.user) }
    private lateinit var binding: FragmentChatBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        send_button.setOnClickListener {
            val list = chatViewModel.chatItemDatas.value!!
//            list.add(
//                ChatRecord(
//                    Date(),
//                    chatViewModel.Owner,
//                    ChatContent(input_content.text.toString())
//                )
//            )
            chatViewModel.chatItemDatas.postValue(list)
            input_content.text?.clear()
            chat_content.adapter!!.notifyDataSetChanged()
        }

        ChatContentAdapter().let {
            chatViewModel.chatItemDatas.observe(
                viewLifecycleOwner,
                Observer(it::submitList)
            )
            binding.chatContent.adapter = it
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
