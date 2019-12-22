package com.eduminication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.bmob.v3.exception.BmobException
import com.eduminication.dao.CourseResourceRepository
import com.eduminication.data.CourseResource
import java.sql.SQLException

class CourseResourceListViewModel : ViewModel() {
    private val courseResourceListRepository = CourseResourceRepository()

    val courseResourceList= MutableLiveData<MutableList<CourseResource>>(mutableListOf())

    fun refreshData(listener:(List<CourseResource>)->Unit= { _ -> }) {
        courseResourceListRepository.getAll { list, exception ->
            if (exception != null || list == null)
                throw SQLException("Unable to get data: $exception")
            courseResourceList.value = list
            listener(list)
        }
    }

    fun add( courseResource: CourseResource,listener: (String?, BmobException?) -> Unit = { _, _ -> }) {
        courseResourceList.value!!.add(courseResource)
        courseResourceListRepository.add(courseResource,listener)
    }
}
