package com.example.domain.repositroy

import com.example.commen.Resource
import com.example.domain.model.Blog

interface GetPagerBlogsRepo {

    suspend fun getPagerBlogs(page: Int, limit: Int): Resource<List<Blog>>

}