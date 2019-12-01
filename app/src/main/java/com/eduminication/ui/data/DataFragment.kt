package com.eduminication.ui.data

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.eduminication.R
import com.eduminication.databinding.FragmentDataBinding
import com.tencent.smtt.sdk.TbsReaderView

class DataFragment : Fragment() {
    private lateinit var binding: FragmentDataBinding
    private val dataViewModel = DataViewModel()
    private var mTbsReaderView: TbsReaderView? = null
    private val fileName = "向阳.pptx"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataBinding.inflate(inflater, container, false)

        binding.fab.setClosedOnTouchOutside(true)

        mTbsReaderView = TbsReaderView(this.activity!!, this)
        rl_tbsView = root.findViewById(R.id.rl_tbsView) as RelativeLayout
        rl_tbsView!!.addView(
            mTbsReaderView, RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        local_display()

        return binding.root
    }


    private fun local_display() {
        val bundle = Bundle()
        bundle.putString("filePath", localFile.path)
        bundle.putString(
            "tempPath", Environment.getExternalStorageDirectory()
                .path
        )
        val result = mTbsReaderView!!.preOpen(parseFormat(fileName), false)
        if (result)
            mTbsReaderView!!.openFile(bundle)
    }

    private fun parseFormat(fileName: String): String {
        return fileName.substring(fileName.lastIndexOf(".") + 1)
    }

}
