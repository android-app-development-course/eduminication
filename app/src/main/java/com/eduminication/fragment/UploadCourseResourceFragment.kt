package com.eduminication.fragment


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.eduminication.MainActivity
import com.eduminication.data.CourseResource
import com.eduminication.databinding.FragmentUploadCourseResourceBinding
import com.eduminication.viewmodel.CourseResourceListViewModel
import kotlinx.android.synthetic.main.fragment_upload_course_resource.*
import java.io.File


class UploadCourseResourceFragment : Fragment() {
    companion object {
        private const val REQUEST_FILE_GET = 1
    }

    private lateinit var pdfFile: File
    private val sharedViewModel by lazy {
        (activity as MainActivity).sharedViewModel
    }

    private val courseResourceListViewModel = CourseResourceListViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUploadCourseResourceBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        select_course_resource.setOnClickListener {
            selectFile()
        }

        upload.setOnClickListener {
            if (sharedViewModel.user.value!!.userType == 1){
                val courseResource = CourseResource(
                    course_resource_name.editText!!.text.toString(),
                    sharedViewModel.user.value!!,
                    course_resource_preview.editText!!.text.toString(),
                    pdfFile,
                    sharedViewModel.courseResorcePos
                )
                courseResourceListViewModel.add(courseResource)
            }else
                Toast.makeText(context, "用户身份为老师才能上传课件", Toast.LENGTH_LONG)
        }
    }

    private fun selectFile() {
        Intent(Intent.ACTION_GET_CONTENT).run {
            type = Intent.normalizeMimeType("application/pdf")
            startActivityForResult(this, REQUEST_FILE_GET)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_FILE_GET && resultCode == Activity.RESULT_OK) {
            pdfFile = File(data?.data!!.path!!)
            file_path.text = "$pdfFile"
        }
    }
}
