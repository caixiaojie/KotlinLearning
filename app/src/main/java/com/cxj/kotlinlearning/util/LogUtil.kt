package com.cxj.kotlinlearning.util

import android.util.Log
//internal 限制在模块内使用
internal var isOpenLog = false

internal fun String.logI(tagObj: Any) {
    if (isOpenLog) {
        Log.i(tagObj.javaClass.simpleName, this)
    }
}