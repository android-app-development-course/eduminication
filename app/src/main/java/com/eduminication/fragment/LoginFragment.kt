package com.eduminication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.eduminication.MainActivity
import com.eduminication.databinding.FragmentLoginBinding
import com.eduminication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    private val userViewModel = UserViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user_name.editText!!.doOnTextChanged { text, _, _, _ ->
            userViewModel.user.value!!.name = text.toString()
        }

        user_password.editText!!.doOnTextChanged { text, _, _, _ ->
            userViewModel.user.value!!.password = text.toString()
        }

        register.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionNavLoginToRegisterFragment())
        }

        login.setOnClickListener {
            if (user_name.editText!!.text.toString() != "" && user_password.editText!!.text.toString() != "") {
                userViewModel.logIn { user, exception ->
                    exception?.let {
                        Toast.makeText(context, "登录错误: $exception", Toast.LENGTH_LONG).show()
                    }
                    (activity as MainActivity).sharedViewModel.user.value = user
                }
                findNavController().navigate(LoginFragmentDirections.actionNavLoginToNavHome())
            } else Toast.makeText(context, "请输入账号和密码", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLoginBinding.inflate(inflater, container, false).root
}
