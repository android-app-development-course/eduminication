package com.eduminication.utils

import androidx.fragment.app.Fragment
import kotlin.reflect.KClass



data class ViewPageFragmentInfo(
    var title:String,
    var fragmentClass:KClass<out Fragment>
)
