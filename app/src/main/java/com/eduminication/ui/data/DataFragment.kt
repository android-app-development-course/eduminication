package com.eduminication.ui.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eduminication.databinding.FragmentDataBinding
import com.tencent.smtt.sdk.TbsReaderView

class DataFragment : Fragment() {
    private lateinit var mTbsReaderView: TbsReaderView
    private lateinit var binding: FragmentDataBinding
    private val dataViewModel = DataViewModel()
    private val fileName = "向阳.pptx"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataBinding.inflate(inflater, container, false)

        binding.fab.setClosedOnTouchOutside(true)

        mTbsReaderView = TbsReaderView(context!!) { _, _, _ -> }

        binding.dataConstraintLayout.addView(mTbsReaderView)


        return binding.root
    }



}
