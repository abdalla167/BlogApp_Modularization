package com.example.blogapp_modularization.screens.home

interface Reducer<S, A> {
    fun reduce(state: S, action: A): S
}