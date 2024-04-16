package com.dicoding.asclepius.Utils

sealed class ResultUser {
    data class Success<out T>(val data: T) : ResultUser()
    data class Error(val exception: Throwable) : ResultUser()
    data class Loading(val isLoading: Boolean) : ResultUser()
}