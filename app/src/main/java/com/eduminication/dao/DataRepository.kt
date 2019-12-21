package com.eduminication.dao

import cn.bmob.v3.exception.BmobException
import com.eduminication.data.BmobData
import com.eduminication.utils.Query

open class DataRepository<T> where T:BmobData {
    open fun add(data: T, listener: (String?, BmobException?) -> Unit = { _, _ -> }) =
        data.add(listener)

    open fun delete(data: T, listener: (BmobException?) -> Unit = { _ -> }) =
        data.delete(listener)

    open fun update(data: T, listener: (BmobException?) -> Unit = { _ -> }) =
        data.update(listener)

    open fun getAll(listener: (MutableList<T>?, BmobException?) -> Unit = { _, _ -> }) =
        Query.getAll(listener)

    open fun getById(id: String, listener: (T, BmobException?) -> Unit = { _, _ -> }) =
        Query.getById(id, listener)
}