package com.eduminication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eduminication.data.CourseResource
import com.eduminication.databinding.FragmentUploadCourseResourceBinding
import com.eduminication.viewmodel.CourseResourceListViewModel
import com.eduminication.viewmodel.CourseResourceListViewModelFactory
import kotlinx.android.synthetic.main.fragment_upload_course_resource.*

class UploadCourseResourceFragment : Fragment() {
    private val uploadCourseResourceViewModel by viewModels<CourseResourceListViewModel> {
        CourseResourceListViewModelFactory()
    }
    private lateinit var courseResource: CourseResource

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =FragmentUploadCourseResourceBinding.inflate(inflater,container,false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseResource.name = title.text.toString()
        courseResource.preview = preview.text.toString()
        file_select_button.setOnClickListener {
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("*/*")
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent, 1)
        }
        upload_button.setOnClickListener{
            uploadCourseResourceViewModel.add(courseResource)
        }
    }
}
