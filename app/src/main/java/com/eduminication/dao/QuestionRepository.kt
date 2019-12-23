package com.eduminication.dao

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.eduminication.data.Question
import io.reactivex.disposables.Disposable

class QuestionRepository :DataRepository<Question>(){
    fun getAllByResourceId(objectId: String, listener: (MutableList<Question>?, BmobException?) -> Unit): Disposable {
        return BmobQuery<Question>().addWhereEqualTo("courseId", objectId).findObjects(object : FindListener<Question>() {
            override fun done(p0: MutableList<Question>?, p1: BmobException?)  =
                listener(p0, p1)
        })
    }

    fun getItemByQuestionId(questionId: String, listener: (MutableList<Question>?, BmobException?) -> Unit): Disposable =
        BmobQuery<Question>().addWhereEqualTo("objectId", questionId).findObjects(object : FindListener<Question>() {
            override fun done(p0: MutableList<Question>?, p1: BmobException?) =
                listener(p0, p1)
        })
}
