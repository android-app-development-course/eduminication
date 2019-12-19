package com.eduminication.data

import cn.bmob.v3.BmobObject
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import cn.bmob.v3.listener.UpdateListener
import io.reactivex.disposables.Disposable


open class BmobData : BmobObject() {
    fun add(listener: (String?, BmobException?) -> Unit): Disposable =
        save(object : SaveListener<String>() {
            override fun done(str: String?, exception: BmobException?) = listener(str, exception)
        })

    fun delete(listener: (BmobException?) -> Unit): Disposable = delete(object : UpdateListener() {
        override fun done(exception: BmobException?) = listener(exception)
    })

    fun update(listener: (BmobException?) -> Unit): Disposable = update(object : UpdateListener() {
        override fun done(exception: BmobException?) = listener(exception)
    })
}
