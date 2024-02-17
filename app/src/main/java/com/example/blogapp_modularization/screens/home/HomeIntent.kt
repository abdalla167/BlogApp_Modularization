package com.example.blogapp_modularization.screens.home



sealed class HomeIntent {
    object FetchData : HomeIntent()
    object ClickedItem : HomeIntent()
}