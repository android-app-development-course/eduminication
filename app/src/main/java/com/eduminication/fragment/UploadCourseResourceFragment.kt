package com.eduminication.fragment


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import cn.bmob.v3.datatype.BmobFile
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UploadFileListener
import com.eduminication.MainActivity
import com.eduminication.data.CourseResource
import com.eduminication.databinding.FragmentUploadCourseResourceBinding
import com.eduminication.viewmodel.CourseResourceListViewModel
import kotlinx.android.synthetic.main.fragment_upload_course_resource.*
import java.io.File


/**
 * A simple [Fragment] subclass.
 */
class UploadCourseResourceFragment : Fragment() {
    companion object {
        private const val REQUEST_FILE_GET = 1
    }

    private var pdfFile: File? = null
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
            pdfFile?.let {
                courseResourceListViewModel.run {
                    var exception: BmobException? = null
                    val courseResource = CourseResource(
                        course_resource_name.editText!!.text.toString(),
                        sharedViewModel.user.value!!,
                        course_resource_preview.editText!!.text.toString(),
                        BmobFile(it).apply {
                            upload(object : UploadFileListener() {
                                override fun done(e: BmobException?) {
                                    Toast.makeText(
                                        context,
                                        if (exception == null) "成功上传"
                                        else {
                                            exception = e
                                            "上传失败: $exception"
                                        },
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            })
                        }
                    )

                    if (exception == null)
                        add(courseResource) { _, e ->
                            Toast.makeText(
                                context,
                                if (e == null) {
                                    courseResourceList.value!!.clear()
                                    "文件成功添加"
                                } else "文件添加失败: $exception",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                }
            }
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
