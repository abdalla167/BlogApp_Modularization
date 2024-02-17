package com.example.blogapp_modularization.screens.home

interface Renderer<S> {
    fun render(state: S)
}