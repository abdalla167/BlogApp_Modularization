package com.example.domain.use_case

import com.example.commen.Resource
import com.example.domain.model.Blog
import com.example.domain.repositroy.GetPagerBlogsRepo
import javax.inject.Inject


class GetBlogsUseCase @Inject constructor( private val blogsRepositry: GetPagerBlogsRepo):GetPagerBlogsRepo
{

    override suspend fun getPagerBlogs(page: Int, limit: Int): Resource<List<Blog>> {
        return  blogsRepositry.getPagerBlogs(page, limit)
    }

}