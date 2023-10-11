package com.worldcountries.common

import android.content.Context
import android.content.res.Configuration
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
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

fun modifyText(subTitle: String, text: String?): SpannableString {
    val fullText = "$subTitle $text"

    val startIndex = fullText.indexOf(subTitle)
    val endIndex = startIndex + subTitle.length

    val spannedString = SpannableString(fullText)

    spannedString.setSpan(
        StyleSpan(Typeface.BOLD),
        startIndex,
        endIndex,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    return spannedString
}

fun getChartColors(context: Context): List<Int> {
    return listOf(
        context.getColor(R.color.chart_light_blue),
        context.getColor(R.color.chart_light_brown),
        context.getColor(R.color.chart_light_red),
        context.getColor(R.color.chart_light_purple),
        context.getColor(R.color.chart_light_green),
        context.getColor(R.color.chart_light_yellow)
    )
}