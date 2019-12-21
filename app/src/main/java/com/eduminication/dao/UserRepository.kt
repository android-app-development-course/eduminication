package com.eduminication.dao

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.eduminication.data.User
import io.reactivex.disposables.Disposable

class UserRepository {
    fun signIn(user: User, listener: (String?, BmobException?) -> Unit): Disposable =
        user.add(listener)

    fun logIn(user: User, listener: (BmobException?) -> Unit): Disposable {
        val query = BmobQuery<User>()
        query.addWhereEqualTo("name",user.name)
        query.addWhereEqualTo("password",user.password)
        query.addWhereEqualTo("userType",user.userType)
        return query.findObjects(object : FindListener<User?>() {
            override fun done(user: MutableList<User?>?, exception: BmobException?) = listener(exception)
        })
    }
}
