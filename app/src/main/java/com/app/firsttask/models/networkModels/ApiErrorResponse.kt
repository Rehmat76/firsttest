package com.app.firsttask.models.networkModels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiErrorResponse (
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String
): Serializable