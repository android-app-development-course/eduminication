package com.eduminication.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.eduminication.data.UserType
import com.eduminication.databinding.FragmentRegisterBinding
import com.eduminication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {
    private val userViewModel = UserViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user_name.editText!!.doOnTextChanged { text, _, _, _ ->
            userViewModel.user.value!!.username = text.toString()
        }

        user_password.editText?.doOnTextChanged { text, _, _, _ ->
            userViewModel.user.value!!.setPassword(text.toString())
        }

        student_type.setOnClickListener {
            userViewModel.user.value!!.userType = UserType.Student
        }

        teacher_type.setOnClickListener {
            userViewModel.user.value!!.userType = UserType.Teacher
        }

        register.setOnClickListener {
            if (user_password.editText!!.text.toString() == confirm_password.editText!!.text.toString()) {
                userViewModel.register{ _, exception ->
                    exception?.let {
                        Toast.makeText(context, "添加错误: $exception", Toast.LENGTH_LONG).show()
                    }
                }
            } else
                Toast.makeText(context, "两次密码不相同", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentRegisterBinding.inflate(inflater, container, false).root
}
