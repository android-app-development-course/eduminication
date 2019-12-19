package com.eduminication.ui.courseResourceList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CourseResourceListViewModel(private val courseResourceListRepository: CourseResourceListRepository): ViewModel (){
    var courseResourceList = MutableLiveData<MutableList<CourseResource>>()

    constructor() : this(CourseResourceListRepository())

    suspend fun refreshData() {
        courseResourceList.value =
            courseResourceListRepository.getCourseResourceList().toMutableList().apply { sortBy { it.id } }
    }
}