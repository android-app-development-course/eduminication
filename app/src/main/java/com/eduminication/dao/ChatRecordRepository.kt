package com.eduminication.dao

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.eduminication.data.ChatRecord
import io.reactivex.disposables.Disposable

class ChatRecordRepository : DataRepository<ChatRecord>(){
    override fun getAll(listener: (MutableList<ChatRecord>?, BmobException?) -> Unit): Disposable =
        BmobQuery<ChatRecord>().findObjects(object : FindListener<ChatRecord>(){
            override fun done(p0: MutableList<ChatRecord>?, p1: BmobException?) =
                listener(p0, p1)
        })
}