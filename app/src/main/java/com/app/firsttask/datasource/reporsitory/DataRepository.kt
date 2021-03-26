package com.app.firsttask.datasource.reporsitory

import android.content.Context
import com.app.firsttask.datasource.network.RetrofitClientInstance
import com.app.firsttask.models.generalModels.UserListModel
import com.app.firsttask.utils.network.ResultWrapper
import com.app.firsttask.utils.network.safeApiCall


class DataRepository(ctx: Context, retroInstance: RetrofitClientInstance) :
    BaseRepository(ctx, retroInstance) {

    // ============================= Remote Data API Calls  ============================= //

    suspend fun getUserList(pageLimit: Int, offset: Int): ResultWrapper<List<UserListModel>> {
        return safeApiCall(dispatcher) {
            getApiService().getUsers(pageLimit, offset)
        }
    }
}