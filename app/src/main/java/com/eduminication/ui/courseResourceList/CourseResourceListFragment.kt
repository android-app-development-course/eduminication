package com.eduminication.ui.courseResourceList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.eduminication.databinding.FragmentCourseResourceListBinding
import kotlinx.android.synthetic.main.fragment_course_resource_list.*
import kotlinx.coroutines.launch

class CourseResourceListFragment : Fragment() {
    private var courseResourceListViewModel = CourseResourceListViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return FragmentCourseResourceListBinding.inflate(inflater, container, false).apply {
            courseResource = CourseResource("课件一", "这个一个测试课件", "http://www.inkwelleditorial.com/pdfSample.pdf")
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        course_resource_recycler_view.adapter = CourseResourceRecylerViewAdapter().apply {
            courseResourceListViewModel.let {
                it.courseResourceList.observe(
                    viewLifecycleOwner,
                    Observer { list -> submitList(list) }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        courseResourceListViewModel.courseResourceList.value = null
        lifecycleScope.launch {
            courseResourceListViewModel.refreshData()
        }
    }
}
