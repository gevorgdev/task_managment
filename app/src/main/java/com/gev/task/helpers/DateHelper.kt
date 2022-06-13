package com.gev.task.helpers

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    @SuppressLint("SimpleDateFormat")
    fun dateToString(date: Date, pattern: String = "dd-MM-yyyy HH:mm:ss"): String {
        val format = SimpleDateFormat(pattern)
        return format.format(date)
    }
}