package com.eduminication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.eduminication.MainActivity
import com.eduminication.databinding.FragmentCourseResourceDetailBinding
import com.eduminication.viewmodel.CourseResourceListViewModel
import com.eduminication.viewmodel.CourseResourceListViewModelFactory
import kotlinx.android.synthetic.main.fragment_course_resource_detail.*
import java.io.File

class CourseResourceDetailFragment : Fragment() {
    private val courseResourceListViewModel by viewModels<CourseResourceListViewModel> {
        CourseResourceListViewModelFactory()
    }

    private val sharedViewModel by lazy {
        (activity as MainActivity).sharedViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menu_floating_button.setClosedOnTouchOutside(true)

        fab_question_list.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(CourseResourceDetailFragmentDirections.actionNavDataToQuestionListFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentCourseResourceDetailBinding.inflate(inflater, container, false).root

    private fun openPdf(pdfFile: File) {
        pdf_view.fromFile(pdfFile).onTap { true }.load()
    }
}
