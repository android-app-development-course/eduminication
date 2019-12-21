package com.eduminication.fragment


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import cn.bmob.v3.datatype.BmobFile
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UploadFileListener
import com.eduminication.data.CourseResource
import com.eduminication.databinding.FragmentUploadCourseResourceBinding
import com.eduminication.viewmodel.CourseResourceListViewModel
import com.eduminication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_upload_course_resource.*
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


/**
 * A simple [Fragment] subclass.
 */
class UploadCourseResourceFragment : Fragment() {
    companion object{
        private const val REQUEST_FILE_GET = 1
    }

    private val courseResourceListViewModel = CourseResourceListViewModel()
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUploadCourseResourceBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = activity?.run {
            ViewModelProviders.of(this)[UserViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        select_course_resource.setOnClickListener {
            selectFile()
        }
        upload.setOnClickListener {
            var courseResource = CourseResource(
                course_resource_name.toString(),
                userViewModel.user.value!!,
                course_resource_preview.toString(),
                FileUri.toString()
            )
            courseResourceListViewModel.add(courseResource)
        }
    }

    private fun selectFile(){
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "*/*"
        }
        if (intent.resolveActivity(activity!!.packageManager) != null) {
            startActivityForResult(intent, REQUEST_FILE_GET)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_FILE_GET && resultCode == Activity.RESULT_OK) {
            val fullFileUri: Uri = data?.data!!
            FileUri.text = "文件路径：" + context?.let { getFilePathForN(it, fullFileUri) }
            var file = BmobFile(File(context?.let { getFilePathForN(it, fullFileUri) }))
            file.uploadblock(object : UploadFileListener() {
                override fun done(e: BmobException) {
                    if (e == null) { //file.getFileUrl()--返回的上传文件的完整地址
                        Toast.makeText(context, "上传文件成功:" + file.getFileUrl(), Toast.LENGTH_LONG)
                    } else {
                        Toast.makeText(context, "上传文件失败：" + e.message, Toast.LENGTH_LONG)
                    }
                }
                override fun onProgress(value: Int) { // 返回的上传进度（百分比）
                }
            })
        }
    }

    private fun getFilePathForN(
        context: Context,
        uri: Uri
    ): String? {
        try {
            val returnCursor =
                context.contentResolver.query(uri, null, null, null, null)
            val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            returnCursor.moveToFirst()
            val name = returnCursor.getString(nameIndex)
            val file = File(context.filesDir, name)
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(file)
            var read = 0
            val maxBufferSize = 1 * 1024 * 1024
            val bytesAvailable: Int = inputStream!!.available()
            val bufferSize = Math.min(bytesAvailable, maxBufferSize)
            val buffers = ByteArray(bufferSize)
            while (inputStream!!.read(buffers).also({ read = it }) != -1) {
                outputStream.write(buffers, 0, read)
            }
            returnCursor.close()
            inputStream!!.close()
            outputStream.close()
            return file.getPath()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return null
    }
}
