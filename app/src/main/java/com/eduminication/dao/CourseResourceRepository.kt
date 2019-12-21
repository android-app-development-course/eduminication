package com.eduminication.dao

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.eduminication.data.CourseResource
import io.reactivex.disposables.Disposable

class CourseResourceRepository :DataRepository<CourseResource>() {
    override fun getAll(listener: (MutableList<CourseResource>?, BmobException?) -> Unit): Disposable =
        BmobQuery<CourseResource>().findObjects(object : FindListener<CourseResource>() {
            override fun done(p0: MutableList<CourseResource>?, p1: BmobException?) =
                listener(p0,p1)
        })
}