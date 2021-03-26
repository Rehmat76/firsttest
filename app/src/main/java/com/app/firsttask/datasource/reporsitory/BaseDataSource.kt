package com.app.firsttask.datasource.reporsitory

import com.app.firsttask.models.networkModels.ApiErrorResponse

interface BaseDataSource {
    fun onPayloadError(error: ApiErrorResponse)
}
