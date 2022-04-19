package com.cxj.kotlinlearning.util.dialogtest

import android.content.Context
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AlertDialog
import com.cxj.kotlinlearning.util.dialog.DialogChain
import com.cxj.kotlinlearning.util.dialog.Interceptor

abstract class BaseDialog(context: Context) : AlertDialog(context),Interceptor{
    private var mChain: DialogChain? = null

    @CallSuper
    override fun intercept(chain: DialogChain?) {
        mChain = chain
    }
    //执行下一个拦截器
    fun chain(): DialogChain? = mChain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.attributes?.width = 800
        window?.attributes?.height = 900
    }
}