package com.eduminication.ui.data

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import com.eduminication.R
import com.github.clans.fab.FloatingActionMenu
import com.tencent.smtt.sdk.TbsReaderView

import java.io.File

class DataFragment : Fragment() {
    private val dataViewModel= DataViewModel()
    private var mTbsReaderView: TbsReaderView? = null
    private val fileName = "向阳.pptx"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_data, container, false)

        val fab = root.findViewById<View>(R.id.fab) as FloatingActionMenu
        fab.setClosedOnTouchOutside(true)

        mTbsReaderView = TbsReaderView(this.activity!!, this)
        rl_tbsView = root.findViewById(R.id.rl_tbsView) as RelativeLayout
        rl_tbsView!!.addView(
            mTbsReaderView, RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        local_display()

        return root
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
