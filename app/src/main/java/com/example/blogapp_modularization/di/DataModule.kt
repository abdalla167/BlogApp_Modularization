package com.example.blogapp_modularization.di

import android.content.Context
import com.example.commen.Constant
import com.example.data.network.ApiService
import com.example.data.repository.GetPagerBlogsRepoImpl
import com.example.data.room.BlogDAO
import com.example.data.room.BlogDataBase
import com.example.domain.repositroy.GetPagerBlogsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object DataModule {


    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }



    @Provides
    fun provideDataBase(@ApplicationContext context: Context): BlogDataBase {
        return BlogDataBase.getInstance(context)
    }

    @Provides
    fun provideDAO(blogDataBase: BlogDataBase): BlogDAO {
        return blogDataBase.getBlogDAO()
    }


    @Provides
    fun provideGetPagerRepo(apiService: ApiService ): GetPagerBlogsRepo {
        return GetPagerBlogsRepoImpl(apiService)
    }



}