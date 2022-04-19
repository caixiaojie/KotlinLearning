package com.cxj.kotlinlearning.util.dialogtest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.cxj.kotlinlearning.R
import com.cxj.kotlinlearning.util.dialog.DialogChain

class DialogsShowActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DialogsShowActivity::class.java)
//                .putExtra()
            context.startActivity(starter)
        }
    }
    private lateinit var dialogChain: DialogChain
    private val bDialog by lazy { BDialog(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialogs_show)
        DialogChain.openLog(true)
        createDialogChain()
        //模拟延迟数据回调
        Handler(Looper.getMainLooper()).postDelayed({
            bDialog.onDataCallback("延迟数据回来了")
        }, 10000)
    }

    private fun createDialogChain() {
        dialogChain = DialogChain.create(3)
            .attach(this)
            .addInterceptor(ADialog(this))
            .addInterceptor(bDialog)
            .addInterceptor(CDialog(this))
            .build()
    }

    override fun onStart() {
        super.onStart()
        dialogChain.process()
    }
}