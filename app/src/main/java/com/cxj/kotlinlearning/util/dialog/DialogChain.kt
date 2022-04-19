package com.cxj.kotlinlearning.util.dialog

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.cxj.kotlinlearning.util.isOpenLog
import com.cxj.kotlinlearning.util.logI

class DialogChain private constructor(
    //弹窗的时候可能需要Activity/Fragment环境
    val activity: FragmentActivity? = null,
    val fragment: Fragment? = null,
    private var interceptors: MutableList<Interceptor>?
) {
    companion object {
        @JvmStatic
        fun create(initialCapacity: Int = 0): Builder {
            return Builder(initialCapacity)
        }

        @JvmStatic
        fun openLog(isOpen: Boolean) {
            isOpenLog = isOpen
        }
    }

    private var index: Int = 0
    fun process() {
        interceptors ?: return//为空则直接返回 不执行下面代码
        when (index) {
            in interceptors!!.indices -> {
                val interceptor = interceptors!![index]
                index++
                interceptor.intercept(this)
            }
            interceptors!!.size -> {
                "===> clearAllInterceptors".logI(this)
                clearAllInterceptors()
            }
        }
    }

    private fun clearAllInterceptors() {
        interceptors?.clear()
        interceptors = null
    }


    open class Builder(private val initialCapacity: Int = 0) {
        private val interceptors by lazy(LazyThreadSafetyMode.NONE) {
            ArrayList<Interceptor>(initialCapacity)
        }
        private var activity: FragmentActivity? = null
        private var fragment: Fragment? = null

        //添加一个拦截器
        fun addInterceptor(interceptor: Interceptor): Builder {
            if (!interceptors.contains(interceptor)) {
                interceptors.add(interceptor)
            }
            return this
        }

        //关联fragment
        fun attach(fragment: Fragment): Builder {
            this.fragment = fragment
            return this
        }

        //关联activity
        fun attach(activity: FragmentActivity): Builder {
            this.activity = activity
            return this
        }

        fun build(): DialogChain {
            return DialogChain(activity, fragment, interceptors)
        }
    }
}