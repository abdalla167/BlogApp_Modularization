package com.example.blogapp_modularization.screens.home

import com.example.commen.Resource
import com.example.domain.model.Blog

interface HomeView {
    fun render(state: Resource<Blog>)
}