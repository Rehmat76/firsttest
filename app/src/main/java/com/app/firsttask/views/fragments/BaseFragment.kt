package com.app.firsttask.views.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.app.firsttask.utils.general.DialogUtils
import com.app.firsttask.viewmodel.BaseAndroidViewModel


abstract class BaseFragment<B : ViewBinding> : Fragment() {

    lateinit var dialog: AlertDialog
    private lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = DialogUtils.getProgressDialog(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        attachViewModel()
    }

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?, b: Boolean): B

    abstract fun initViews()

    abstract fun attachViewModel()


    fun showProgressDialog(show: Boolean) {
        if (dialog != null && show) {
            if (!dialog.isShowing)
                dialog.apply {
                    show()
                }
        } else if (dialog != null && !show) {
            if (dialog.isShowing)
                dialog.dismiss()
        }
    }

    protected fun setupGeneralViewModel(generalViewModel: BaseAndroidViewModel) {

        with(generalViewModel)
        {
            snackbarMessage.observe(viewLifecycleOwner, Observer {
//                it.getContentIfNotHandled()?.let { showAlertDialog(it) }
            })

            progressBar.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let { showProgressDialog(it) }
            })
        }
    }

    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        //should check null because in airplane mode it will be null
        return netInfo != null && netInfo.isConnected
    }

    private fun onBackPress() {
        onBackPress()
    }

}
