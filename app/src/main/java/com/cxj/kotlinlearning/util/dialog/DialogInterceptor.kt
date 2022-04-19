package com.cxj.kotlinlearning.util.dialog

import androidx.annotation.CallSuper

class DialogInterceptor : Interceptor {
    private var mChain: DialogChain? = null

    @CallSuper
    override fun intercept(chain: DialogChain?) {
        mChain = chain
    }
    //执行下一个拦截器
    fun chain(): DialogChain? = mChain
}