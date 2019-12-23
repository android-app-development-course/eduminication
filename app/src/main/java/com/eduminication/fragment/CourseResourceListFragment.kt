package com.eduminication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.eduminication.adapter.CourseResourceRecylerViewAdapter
import com.eduminication.data.CourseResource
import com.eduminication.databinding.FragmentCourseResourceListBinding
import com.eduminication.viewmodel.CourseResourceListViewModel
import com.eduminication.viewmodel.CourseResourceListViewModelFactory
import kotlinx.android.synthetic.main.fragment_course_resource_list.*
import kotlinx.coroutines.launch


class CourseResourceListFragment : Fragment() {
    private val courseResourceListViewModel by viewModels<CourseResourceListViewModel> {
        CourseResourceListViewModelFactory()
    }

    var courseType = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentCourseResourceListBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        course_resource_recycler_view.adapter = CourseResourceRecylerViewAdapter()
            .apply {
                courseResourceListViewModel.let {
                    it.courseResourceList.observe(
                        viewLifecycleOwner,
                        Observer { list ->
                            list?.let {
                                val filtered = mutableListOf<CourseResource>()
                                for (resource in list)
                                    if (resource.courseType == courseType)
                                        filtered.add(resource)
                                submitList(filtered)
                            }
                        }
                    )
                }
            }
    }

    override fun onResume() {
        super.onResume()
        courseResourceListViewModel.courseResourceList.value = null
        lifecycleScope.launch { courseResourceListViewModel.refreshData() }
    }
}
