package com.eduminication.utils

import android.content.Context
import com.google.common.io.ByteStreams.copy
import java.io.File
import java.io.OutputStream
import java.net.URL

//TODO deal with unknown certificate
fun fetchUrl2Location(srcUrl: URL, outputStream: OutputStream) {
    copy(srcUrl.openConnection().getInputStream(), outputStream)
}

fun fetchUrl2Cache(context: Context, url: URL) {
    fetchUrl2Location(
        url, File.createTempFile(url.toString(), null, context.cacheDir).outputStream()
    )
}
