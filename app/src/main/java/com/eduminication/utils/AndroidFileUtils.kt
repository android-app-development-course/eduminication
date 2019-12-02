package com.eduminication.utils

import android.content.Context
import android.webkit.MimeTypeMap
import com.google.common.io.ByteStreams.copy
import java.io.File
import java.io.OutputStream
import java.net.URL

//TODO deal with unknown certificate
fun fetchUrl2Location(srcUrl: URL, outputStream: OutputStream) {
    copy(srcUrl.openConnection().getInputStream(), outputStream)
}

fun fetchUrl2Cache(context: Context, url: URL): File {
    val cacheFile = File.createTempFile(
        md5Encode(url.toString()),
        MimeTypeMap.getFileExtensionFromUrl(url.toString()),
        context.cacheDir
    )
    fetchUrl2Location(url, cacheFile.outputStream())
    return cacheFile
}
