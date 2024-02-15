package com.example.blogapp_modularization.di

import com.example.domain.repositroy.GetPagerBlogsRepo
import com.example.domain.use_case.GetBlogsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object DoaminModule {

    @Provides
    fun proviedGetBlogUseCase(blogsRepositry: GetPagerBlogsRepo):GetBlogsUseCase
    {
       return GetBlogsUseCase(blogsRepositry)
    }

}