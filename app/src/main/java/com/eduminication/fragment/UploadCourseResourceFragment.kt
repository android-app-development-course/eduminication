package com.eduminication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eduminication.databinding.FragmentUploadCourseResourceBinding

class UploadCourseResourceFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )=FragmentUploadCourseResourceBinding.inflate(inflater,container,false).root
}
