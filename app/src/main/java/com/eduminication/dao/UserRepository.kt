package com.eduminication.dao

import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.eduminication.data.User
import io.reactivex.disposables.Disposable

class UserRepository {
    fun add(user: User, listener: (String?, BmobException?) -> Unit): Disposable =
        user.signUp(object : SaveListener<String>() {
            override fun done(str: String?, exception: BmobException?) = listener(str, exception)
        })

    fun delete(user: User, listener: (BmobException?) -> Unit): Disposable =
        user.delete(object : cn.bmob.v3.listener.UpdateListener() {
            override fun done(exception: cn.bmob.v3.exception.BmobException?) = listener(exception)
        })

    fun update(user: User, listener: (BmobException?) -> Unit): Disposable =
        user.update(object : cn.bmob.v3.listener.UpdateListener() {
            override fun done(exception: cn.bmob.v3.exception.BmobException?) = listener(exception)
        })

    fun logIn(user: User, listener: (BmobException?) -> Unit): Disposable =
        user.login(object : SaveListener<User?>() {
            override fun done(user: User?, exception: BmobException?) = listener(exception)
        })
}
