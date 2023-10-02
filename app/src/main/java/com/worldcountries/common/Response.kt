package com.worldcountries.common

import androidx.annotation.StringRes

sealed interface Response<out T> {

    class Success<T>(val data: T) : Response<T>
    class Error<T>(@StringRes val messageId: Int) : Response<T>
}