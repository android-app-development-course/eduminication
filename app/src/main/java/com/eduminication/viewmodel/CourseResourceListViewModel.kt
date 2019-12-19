package com.eduminication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eduminication.dao.CourseResourceListRepository
import com.eduminication.data.CourseResource

class CourseResourceListViewModel(private val courseResourceListRepository: CourseResourceListRepository): ViewModel (){
    var courseResourceList = MutableLiveData<MutableList<CourseResource>>()

    constructor() : this(CourseResourceListRepository())

    suspend fun refreshData() {
        courseResourceList.value =
            courseResourceListRepository.getCourseResourceList().toMutableList().apply { sortBy { it.id } }
    }
}
