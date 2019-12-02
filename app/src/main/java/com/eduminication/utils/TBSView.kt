package com.eduminication.utils

import android.content.Context
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.TbsReaderView
import java.net.URL


class TBSView(context: Context, callback: TbsReaderView.ReaderCallback) {
    init {
        QbSdk.initX5Environment(context.applicationContext, null)
    }

    var tbsReaderView = TbsReaderView(context, callback)

    var src: String = ""
        set(value) {
            fetchUrl2Cache(tbsReaderView.context , URL(src))

            field = value
        }
}
