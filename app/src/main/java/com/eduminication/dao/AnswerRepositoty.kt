package com.eduminication.dao

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.eduminication.data.Answer
import io.reactivex.disposables.Disposable

class AnswerRepository: DataRepository<Answer>(){
    fun getItemByQuestionId(questionId: String, listener: (Answer?, BmobException?) -> Unit): Disposable =
        BmobQuery<Answer>().addWhereEqualTo("questionId", questionId).findObjects(object : FindListener<Answer>() {
            override fun done(p0: MutableList<Answer>?, p1: BmobException?) {
                if (p0?.size !=0)
                    listener(p0?.first(), p1)
            }
        })
}