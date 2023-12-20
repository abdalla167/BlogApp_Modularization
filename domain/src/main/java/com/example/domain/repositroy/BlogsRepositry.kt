package com.example.domain.repositroy

import com.example.domain.model.Blog
import retrofit2.Response

interface BlogsRepositry
{
    suspend fun getBlogs():List<Blog>
}