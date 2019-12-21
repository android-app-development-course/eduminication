package com.eduminication.utils

import android.util.Log
import cn.bmob.v3.BmobObject
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.QueryListener
import io.reactivex.disposables.Disposable
import java.lang.reflect.ParameterizedType

object Query {
    fun <T : BmobObject> getAll(
        listener: (MutableList<T>?, BmobException?) -> Unit = { _, _ -> }
    ): Disposable {
        val query = BmobQuery<T>()

        class TFindListener : FindListener<T>() {
            override fun done(list: MutableList<T>?, exception: BmobException?) =
                listener(list, exception)
        }

        val parameterObject = TFindListener()
        val parameterClass = parameterObject.javaClass
        Log.d("Query", "parameter class:$parameterClass")
        val superClass = parameterClass.genericSuperclass
        Log.d("Query","generic super class:$superClass")
        val actualTypeArguments =
            (superClass as ParameterizedType).actualTypeArguments[0]
        Log.d("Query", "actual type argument:$actualTypeArguments")

        val cast = actualTypeArguments as Class<T>//error cast
        Log.d("Query","cast to Class<T>:$cast")

        return query.findObjects(TFindListener())
    }

    fun <T : BmobObject> getById(
        id: String,
        listener: (T, BmobException?) -> Unit = { _, _ -> }
    ): Disposable {
        val query = BmobQuery<T>()

        class TQueryListener : QueryListener<T>() {
            override fun done(data: T, exception: BmobException?) = listener(data, exception)
        }

        return query.getObject(id, TQueryListener())
    }
}