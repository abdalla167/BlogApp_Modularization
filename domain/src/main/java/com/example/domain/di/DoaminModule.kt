package com.example.domain.di

import com.example.domain.repositroy.BlogsRepositry
import com.example.domain.use_case.GetBlogsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DoaminModule {

    @Provides
    fun proviedGetBlogUseCase(blogsRepositry: BlogsRepositry):GetBlogsUseCase
    {
       return GetBlogsUseCase(blogsRepositry)
    }

}