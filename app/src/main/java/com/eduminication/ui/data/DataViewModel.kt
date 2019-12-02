package com.eduminication.ui.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL

class DataViewModel(
    val context: Context,
    val filePath: MutableLiveData<URL> = MutableLiveData(
        URL("file:///" + context.getExternalFilesDir(null) + "/test.pptx")
    )
) : ViewModel()
