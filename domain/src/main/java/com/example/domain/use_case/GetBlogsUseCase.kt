package com.example.domain.use_case

import com.example.commen.Resource
import com.example.domain.model.Blog
import com.example.domain.repositroy.BlogsRepositry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetBlogsUseCase @Inject constructor( private val blogsRepositry: BlogsRepositry)
{

     operator fun  invoke():Flow<Resource<List<Blog>>> = flow {

        emit(Resource.Loading(null))
        try {

            val response =blogsRepositry.getBlogs()
            emit(Resource.Success(data = response))


        }catch (e:Exception)
        {
            emit(Resource.Error("Error Massage "))
        }
    }

}