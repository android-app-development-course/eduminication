package com.eduminication.dao

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.QueryListener
import com.eduminication.data.BmobData
import io.reactivex.disposables.Disposable

open class DataRepository<T> where T:BmobData {
    open fun add(data: T, listener: (String?, BmobException?) -> Unit = { _, _ -> }) =
        data.add(listener)

    open fun delete(data: T, listener: (BmobException?) -> Unit = { _ -> }) =
        data.delete(listener)

    open fun update(data: T, listener: (BmobException?) -> Unit = { _ -> }) =
        data.update(listener)

    open fun getAll(listener: (MutableList<T>?, BmobException?) -> Unit = { _, _ -> }): Disposable =
        BmobQuery<T>().findObjects(object : FindListener<T>() {
            override fun done(data: MutableList<T>?, exception: BmobException?) {
                listener(data, exception)
            }
        })

    open fun getById(id: String, listener: (T, BmobException?) -> Unit = { _, _ -> }): Disposable =
        BmobQuery<T>().getObject(id, object : QueryListener<T>() {
            override fun done(data: T, exception: BmobException?) = listener(data, exception)
        })
}