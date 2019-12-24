package com.eduminication.dao

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.QueryListener
import com.eduminication.data.Answer
import io.reactivex.disposables.Disposable

class AnswerRepository: DataRepository<Answer>() {
    fun getItemByQuestionId(
        questionId: String,
        listener: (MutableList<Answer>?, BmobException?) -> Unit
    ): Disposable =
        BmobQuery<Answer>().addWhereEqualTo("questionId", questionId).findObjects(object :
            FindListener<Answer>() {
            override fun done(p0: MutableList<Answer>?, p1: BmobException?) {
                listener(p0, p1)
            }
        })

    override fun getById(
        id: String,
        listener: (Answer, BmobException?) -> Unit
    ): Disposable =
        BmobQuery<Answer>().getObject(id, object : QueryListener<Answer>() {
            override fun done(data: Answer, exception: BmobException?) = listener(data, exception)
        })
}
