package com.eduminication.utils

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.QueryListener
import io.reactivex.disposables.Disposable

object Query {
    fun <T> getAll(listener: (MutableList<T>?, BmobException?) -> Unit = { _, _ -> }): Disposable {
        val query = BmobQuery<T>()
        return query.findObjects(object : FindListener<T>() {
            override fun done(list: MutableList<T>?, exception: BmobException?) =
                listener(list, exception)
        })
    }

    fun <T> getById(id: String, listener: (T, BmobException?) -> Unit = { _, _ -> }): Disposable {
        val query = BmobQuery<T>()
        return query.getObject(id, object : QueryListener<T>() {
            override fun done(data: T, exception: BmobException?) = listener(data, exception)
        })
    }
}