package com.eduminication.ui.resourceDetail

import android.content.Context
import androidx.lifecycle.ViewModel
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import com.koushikdutta.ion.builder.Builders
import java.io.File

class DataViewModel(
    context: Context,
    val url: String,
    callback: FutureCallback<File?>
) : ViewModel() {
    val ionInstance: Builders.Any.B? = Ion.with(context).load(url)

    init {
        ionInstance
            ?.write(File(context.cacheDir.absolutePath, url.toHashSet().asSequence().toString()))
            ?.setCallback(callback)
    }
}
