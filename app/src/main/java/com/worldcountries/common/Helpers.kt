package com.worldcountries.common

import android.content.Context
import android.content.res.Configuration
import com.worldcountries.R
import java.io.IOException

suspend inline fun <T> apiCall(crossinline call: suspend () -> T): Response<T> {
    return try {
        Response.Success(data = call())
    } catch (e: IOException) {
        Response.Error(messageId = R.string.network)
    } catch (e: Exception) {
        Response.Error(R.string.unknown)
    }
}

fun getGridSpan(context: Context) =
    if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3