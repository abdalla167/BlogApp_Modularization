package com.example.data.network

import com.example.commen.Constant
import com.example.data.network.model.BlogsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService
{

    @GET("post")
    suspend fun getBlogsPagination(
        @Header("app-id") appId: String = Constant.APP_ID,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<BlogsDTO>

}