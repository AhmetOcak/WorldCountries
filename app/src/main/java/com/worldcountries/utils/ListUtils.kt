package com.worldcountries.utils

import android.content.Context
import android.content.res.Configuration

fun getGridSpan(context: Context) =
    if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3