package com.eduminication.fragment

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.eduminication.databinding.FragmentDataBinding
import kotlinx.android.synthetic.main.fragment_data.*
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

class CourseResourceDetailFragment : Fragment() {
    private lateinit var binding: FragmentDataBinding
    private val args: CourseResourceDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        EasyPermissions.requestPermissions(this, "", 0, Manifest.permission.INTERNET)
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
