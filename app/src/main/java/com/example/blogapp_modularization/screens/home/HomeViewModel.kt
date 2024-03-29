package com.example.blogapp_modularization.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.commen.Resource
import com.example.data.pagging.BlogRemoteMediator
import com.example.data.room.BlogDAO
import com.example.domain.model.Blog
import com.example.domain.use_case.GetBlogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBlogsUseCase: GetBlogsUseCase,
    private val blogDAO: BlogDAO

) : ViewModel() , HomeViewModelContract {

    private val _blogs: MutableStateFlow<Resource<Flow<PagingData<Blog>>>> = MutableStateFlow(Resource.Loading(null))
    val blogs: StateFlow<Resource<Flow<PagingData<Blog>>>> = _blogs.asStateFlow()


    override fun processIntent(intent: HomeIntent) {

        when (intent) {
            is HomeIntent.FetchData ->
                {
                    _blogs.value = Resource.Success(getBlogsPaging())
                }
            HomeIntent.ClickedItem -> TODO()

        }
    }

    fun getBlogsPaging(): Flow<PagingData<Blog>> {
            @OptIn(ExperimentalPagingApi::class)
            return Pager(
                config = PagingConfig(pageSize = 10, prefetchDistance = 5),
                remoteMediator = BlogRemoteMediator(
                    getPagerBlogsRepo = getBlogsUseCase,
                    blogDAO = blogDAO
                )
                 )
            {
                blogDAO.getAllBlogItems()
            }.flow.cachedIn(viewModelScope)

                }


}