package com.example.data.repository

import com.example.commen.Resource
import com.example.data.mapper.toDomain
import com.example.data.network.ApiService
import com.example.data.network.utils.SafeApiRequest
import com.example.domain.model.Blog
import com.example.domain.repositroy.GetPagerBlogsRepo
import javax.inject.Inject

class GetPagerBlogsRepoImpl @Inject constructor(private val apiService: ApiService):GetPagerBlogsRepo {

    private val safeApiRequest = object : SafeApiRequest() {}

    override suspend fun getPagerBlogs(page: Int, limit: Int): Resource<List<Blog>> {

        return try {



            val response = safeApiRequest.safeApiRequest {
                apiService.getBlogsPagination(page = page, limit = limit)
            }


           // val response =apiService.getBlogsPagination(page = page, limit = limit)
            if(response.isSuccessful)
            {
                val body = response.body()?.data?.toDomain()
                Resource.Success(body)

            } else {
                Resource.Error(response.errorBody()?.string())
            }

        }
        catch (e:Exception)
        {
            Resource.Error(e.localizedMessage)


        }
    }
}