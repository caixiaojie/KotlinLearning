package com.cxj.kotlinlearning.util.dialog

interface Interceptor {
    fun intercept(chain: DialogChain?)
}