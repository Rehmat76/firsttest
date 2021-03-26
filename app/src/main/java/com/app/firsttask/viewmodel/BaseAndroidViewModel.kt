package com.app.firsttask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.firsttask.models.networkModels.BaseResponse
import com.app.firsttask.models.networkModels.ErrorResponse
import com.app.firsttask.utils.general.OneShotEvent
import com.app.firsttask.utils.network.ResultWrapper


open class BaseAndroidViewModel() : ViewModel() {


    val snackbarMessage = MutableLiveData<OneShotEvent<String>>()
    val errorResponse = MutableLiveData<OneShotEvent<ErrorResponse>>()
    val progressBar = MutableLiveData<OneShotEvent<Boolean>>()
    val loader = MutableLiveData<OneShotEvent<Boolean>>()
    var isError = MutableLiveData<Boolean>()


    protected fun showSnackbarMessage(message: String) {
        snackbarMessage.value = OneShotEvent(message)
    }

    protected fun showNetworkError() {
        snackbarMessage.value = OneShotEvent("Something went wrong \n Please try again later")
    }

    protected fun handleErrorType(error: ResultWrapper<Any>) {
        isError.value = true
        when (error) {
            is ResultWrapper.NetworkError ->
                showNetworkError()
            is ResultWrapper.GenericError ->
                showSnackbarMessage("" + error.error?.message)
        }
    }

    protected fun isSuccess(result: BaseResponse): Boolean {
        if (result.code == 200) {
            return true
        } else {
            showSnackbarMessage(result.message)
            return false
        }
    }


    protected fun showProgressBar(show: Boolean) {
        progressBar.value = OneShotEvent(show)
    }


}