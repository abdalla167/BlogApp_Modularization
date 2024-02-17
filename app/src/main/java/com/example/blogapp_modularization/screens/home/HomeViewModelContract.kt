package com.example.blogapp_modularization.screens.home

import androidx.compose.runtime.State
import androidx.paging.PagingData
import com.example.domain.model.Blog
import kotlinx.coroutines.flow.Flow

interface HomeViewModelContract {
    fun processIntent(intent: HomeIntent)
}