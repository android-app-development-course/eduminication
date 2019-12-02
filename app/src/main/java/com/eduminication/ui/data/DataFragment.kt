package com.eduminication.ui.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eduminication.databinding.FragmentDataBinding
import com.tencent.smtt.sdk.TbsReaderView


class DataFragment : Fragment(), TbsReaderView.ReaderCallback{
    private var mTbsReaderView: TbsReaderView ?= null
    private lateinit var binding: FragmentDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataBinding.inflate(inflater, container, false)
        binding.fab.setClosedOnTouchOutside(true)

        return binding.root
    }


    override fun onCallBackAction(p0: Int?, p1: Any?, p2: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
