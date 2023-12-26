package com.worldcountries.utils

import android.content.Context
import com.worldcountries.R

object ChartUtils {

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
}