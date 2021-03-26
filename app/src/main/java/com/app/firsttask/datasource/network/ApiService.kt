package com.app.firsttask.datasource.network

import com.app.firsttask.models.generalModels.UserListModel
import retrofit2.http.*

@JvmSuppressWildcards
interface ApiService {

    @GET("characters?")
    suspend fun getUsers(@Query("limit") pageNumber: Int, @Query("offset") offset: Int): List<UserListModel>

}