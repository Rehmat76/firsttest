package com.app.firsttask.datasource.reporsitory

import android.content.Context
import com.app.firsttask.datasource.network.ApiService
import com.app.firsttask.datasource.network.RetrofitClientInstance
import com.app.firsttask.utils.network.ErrorUtils
import com.app.firsttask.utils.network.isOnline
import com.google.gson.Gson

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


open class BaseRepository(ctx: Context,var retroInstance: RetrofitClientInstance) {

    var context: Context = ctx
    val dispatcher: CoroutineDispatcher = Dispatchers.IO

    var gson = Gson()


    fun getApiService(): ApiService {
        return retroInstance.getService()
    }


    fun checkInternetConnection(callback: BaseDataSource): Boolean {
        if (!isOnline(context)) {
            callback.onPayloadError(
                ErrorUtils.parseError(
                    "{\"message\":\"Please check internet connection and try again\",\"code\":\"0\",\"name\":\"error\"}"
                )
            )
            return true
        }
        return false
    }


}