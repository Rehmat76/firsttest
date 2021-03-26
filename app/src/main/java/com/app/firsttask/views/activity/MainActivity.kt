package com.app.firsttask.views.activity

import androidx.lifecycle.Observer
import com.app.firsttask.databinding.ActivityMainBinding
import com.app.firsttask.viewmodel.UserProfileViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var binding: ActivityMainBinding

    private var clearbackStack: Boolean = false

    private val viewModel: UserProfileViewModel by viewModel()

    override fun getViewBinding(): ActivityMainBinding {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding
    }

    override fun initViews() {

        setupViewModel()

    }


    private fun exit() {
        finish()
    }


    private fun setupViewModel() {
        with(viewModel)
        {

            snackbarMessage.observe(this@MainActivity, Observer {
                it.getContentIfNotHandled()?.let {
//                    showAlertDialog(it)
                }
            })

            progressBar.observe(this@MainActivity, Observer {
                it.getContentIfNotHandled()?.let {
                    showProgressDialog(it)
                }
            })


        }


    }

    override fun onBackPressed() {
        if (clearbackStack) {
            exit()
        } else {
            super.onBackPressed()
        }
    }

}