package com.worldcountries.utils

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan

fun formatText(subTitle: String, text: String?): SpannableString {
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