package com.eduminication.utils

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.TbsReaderView
import java.net.URL


class TBSView(context: Context, attrs: AttributeSet) :
    MaterialCardView(context, attrs) {
    companion object {
        private var isX5Initialized = false
    }

    var activity = if (context is Activity) context else null

    private var tbsReaderView = activity?.let { TbsReaderView(activity) { _, _, _ -> } }

    init {
        if (!isX5Initialized) {
            QbSdk.initX5Environment(context.applicationContext, null)
            isX5Initialized = true
        }
        addView(tbsReaderView)
    }

    var src: URL? = null
        set(value) {
            value?.let {
                val file = fetchUrl2Cache(context, value)
                val bundle = Bundle()
                bundle.putString("filePath", file.absolutePath)
                tbsReaderView!!.openFile(bundle)
            }
            field = value
        }


}
