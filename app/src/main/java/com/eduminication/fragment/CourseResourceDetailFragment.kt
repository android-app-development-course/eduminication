package com.eduminication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.eduminication.databinding.FragmentCourseResourceDetailBinding
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.fragment_course_resource_detail.*
import java.io.File

class CourseResourceDetailFragment : Fragment() {

    private val argsLazy by navArgs<CourseResourceDetailFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menu_floating_button.setClosedOnTouchOutside(true)

        fab_question_list.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(CourseResourceDetailFragmentDirections.actionNavDataToQuestionListFragment())
        }

        Ion.with(context).load(argsLazy.url).progressBar(progress_bar)
            .write(File(activity!!.cacheDir.absolutePath + "/" + argsLazy.url.toHashSet().toArray().toString()))
            .setCallback { e, result ->
                if (e != null && result != null)
                    Toast.makeText(context, "下载失败", Toast.LENGTH_LONG).show()
                else {
                    openPdf(result)
                    Toast.makeText(context, "下载成功", Toast.LENGTH_LONG).show()
                }
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
