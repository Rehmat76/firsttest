package com.app.firsttask.views.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewbinding.ViewBinding
import com.app.firsttask.utils.application.openActivityWithExist
import com.app.firsttask.utils.general.DialogUtils

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {

    private var dialog: AlertDialog? = null
    private lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.MODE_NIGHT_YES
        binding = getViewBinding()
        setContentView(binding.root)
        dialog = DialogUtils.getProgressDialog(this)
        initViews()
    }

    abstract fun getViewBinding(): B

    abstract fun initViews()


    fun gotoMainActivity() {
        openActivityWithExist(MainActivity::class.java)
    }


    fun showProgressDialog(show: Boolean) {
        if (dialog != null && show) {
            if (!dialog!!.isShowing)
                dialog!!.apply {
                    show()
                }
        } else if (dialog != null && !show) {
            if (dialog!!.isShowing)
                dialog!!.dismiss()
        }
    }

}
