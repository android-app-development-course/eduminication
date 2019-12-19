package com.eduminication.fragment

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.eduminication.databinding.FragmentDataBinding
import com.eduminication.ui.resourceDetail.CourseResourceDetailFragmentArgs
import com.eduminication.ui.resourceDetail.CourseResourceDetailFragmentDirections
import com.eduminication.viewmodel.CourseResourceDetailViewModel
import com.koushikdutta.async.future.FutureCallback
import kotlinx.android.synthetic.main.fragment_data.*
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

class CourseResourceDetailFragment : Fragment() {
    private lateinit var dataViewModel: CourseResourceDetailViewModel
    private lateinit var binding: FragmentDataBinding
    private val args: CourseResourceDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        EasyPermissions.requestPermissions(this, "", 0, Manifest.permission.INTERNET)
        dataViewModel =
            CourseResourceDetailViewModel(context!!, args.url,
                FutureCallback { e, result ->
                    if (result != null) {
                        Toast.makeText(context, "File download complete", Toast.LENGTH_LONG).show()
                        openPdf(result)
                    } else {
                        var errorStr = "Error downloading file "
                        e?.let { errorStr += it }
                        Toast.makeText(context, errorStr, Toast.LENGTH_LONG).show()
                    }
                    progress_bar.visibility = View.GONE
                })
        dataViewModel.ionInstance?.progressBar(progress_bar)
        binding.menuFloatingButton.setClosedOnTouchOutside(true)

        fab_question_list.setOnClickListener{
            Navigation.findNavController(view).navigate(CourseResourceDetailFragmentDirections.actionNavDataToQuestionListFragment())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun openPdf(pdfFile: File) {
        binding.pdfView.fromFile(pdfFile).onTap { true }.load()
    }
}